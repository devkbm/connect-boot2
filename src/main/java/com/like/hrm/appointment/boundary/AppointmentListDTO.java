package com.like.hrm.appointment.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.board.boundary.ArticleDTO;
import com.like.board.domain.model.vo.Period;
import com.like.hrm.appointment.domain.model.AppointmentChangeInfo;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.AppointmentList;
import com.like.hrm.appointment.domain.model.AppointmentRegister;
import com.like.hrm.appointment.domain.model.QAppointmentList;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AppointmentListDTO {

	@Data
	public static class SearchAppointmentList implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QAppointmentList qType = QAppointmentList.appointmentList;
		
		// @NotEmpty(message = "발령번호는 필수 값입니다.")
		private String ledgerId;
		
		private Long listId;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				//.and(equalLedgerId(ledgerId))
				.and(equalListId(listId));
			
			
			return builder;
		}
		
		private BooleanExpression equalLedgerId(String ledgerId) {						
			return qType.ledger.id.eq(ledgerId);
		}
		
		private BooleanExpression equalListId(Long listId) {
			if (StringUtils.isEmpty(listId)) {
				return null;
			}
			
			return qType.listId.eq(listId);
		}
	}
	
	@Data	
	@AllArgsConstructor
	@Builder
	public static class SaveAppointmentList implements Serializable {
												
		private static final long serialVersionUID = -339266416839829125L;
		
		private String ledgerId;
		
		private Long listId;
				
		private Long sequence;
			
		@NotEmpty
		private String empId;					
							
		private String appointmentCode;
		
		private LocalDate appointmentFromDate;
						
		private LocalDate appointmentToDate;		
		
		private List<ChangeInfo> changeInfoList = new ArrayList<>();
				
		public SaveAppointmentList() {
			this.changeInfoList = new ArrayList<>();
		}		
		
		public AppointmentList newEntity() {
			AppointmentList entity = new AppointmentList(this.empId
											  ,this.appointmentCode							  
											  ,this.appointmentFromDate
											  ,this.appointmentToDate);
						
			for (ChangeInfo info : changeInfoList ) {
				entity.addChangeInfo(this.newChangeInfo(entity, info));
			}
			
			return entity;
		}

				
		public AppointmentList modifyEntity(AppointmentList entity) {
			entity.modifyEntity(getAppointmentCode()
							   ,getAppointmentFromDate()
							   ,getAppointmentToDate()
							   ,null);
										
			for (ChangeInfo info : changeInfoList ) {
				if (info.getId() != null) {
					AppointmentChangeInfo ledgerChangeInfo = entity.getChangeInfo(info.getId());  													
					ledgerChangeInfo.changeCode(info.getChangeCode()
											   ,info.getSequence());					
				} else {
					entity.addChangeInfo(this.newChangeInfo(entity, info));
				}
			}
			
			return entity;
		}
		
		public static SaveAppointmentList convert(AppointmentList entity) {
			
			List<ChangeInfo> list = new ArrayList<>();
			
			if (entity.getChangeInfoList() != null) {
				for (AppointmentChangeInfo info : entity.getChangeInfoList()) {
					list.add(new ChangeInfo(info.getId()
										   ,info.getChangeType().toString()
										   ,info.getChangeTypeDetail()
										   ,info.getChangeCode()
										   ,info.getSequence()));
				}
			}
			
			Optional<AppointmentRegister> register = Optional.ofNullable(entity.getLedger());			
			
			return SaveAppointmentList
					.builder()
					.ledgerId(register.map(AppointmentRegister::getId).orElse(null))
					.listId(entity.getListId())
					.sequence(entity.getSequence())
					.appointmentCode(entity.getAppointmentCode())
					.empId(entity.getEmpId())
					.appointmentFromDate(entity.getAppointmentFromDate())
					.appointmentToDate(entity.getAppointmentToDate())
					.changeInfoList(list)
					.build();		
		}
		
		private AppointmentChangeInfo newChangeInfo(AppointmentList entity, ChangeInfo info) {
			return new AppointmentChangeInfo(entity
					                   ,HrmTypeEnum.valueOf(info.getChangeType())
					                   ,info.getChangeTypeDetail()								                            
					                   ,info.getChangeCode()	
					                   ,info.getSequence());
		}
		
	}
			
	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	public static class ChangeInfo implements Serializable {
		
		private static final long serialVersionUID = -83012562627190252L;

		Long id;
		
		String changeType;
		
		String changeTypeDetail;
		
		String changeCode;
		
		Integer sequence;
		
		public static List<ChangeInfo> convert(List<AppointmentCodeDetail> detailList) {
			
			List<ChangeInfo> list = new ArrayList<>();
			
			for (AppointmentCodeDetail detail : detailList) {
				list.add(new ChangeInfo(null
									   ,detail.getChangeType().getCode()
									   ,detail.getChangeTypeDetail()
									   ,null
									   ,detail.getSequence()));
			}
			
			return list;
		}
	}
}
