package com.like.hrm.appointment.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerChangeInfo;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.model.QLedger;
import com.like.hrm.appointment.domain.model.QLedgerList;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LedgerDTO {		
	
	@Data
	public static class SearchLedger implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QLedger qType = QLedger.ledger;
				
		private String ledgerId;
				
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeLedgerId(ledgerId));
						
			return builder;
		}
		
		private BooleanExpression likeLedgerId(String ledgerId) {
			if (StringUtils.isEmpty(ledgerId)) {
				return null;
			}
			
			return qType.ledgerId.like(ledgerId);
		}
				
	}
	
	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveLedger implements Serializable {
												
		private static final long serialVersionUID = -339266416839829125L;

		private String ledgerId;
			
		private String appointmentType;					
								
		private LocalDate registrationDate;
						
		private String comment;
		
		public Ledger newEntity() {
			return new Ledger(this.ledgerId
							  ,this.appointmentType
							  ,this.registrationDate							  
							  ,this.comment);
		}
		
		public Ledger modifyEntity(Ledger entity) {
			entity.changeInfo(this.appointmentType												 
							 ,this.comment);
			
			return entity;
		}
	}
	
	@Data
	public static class SearchLedgerList implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QLedgerList qType = QLedgerList.ledgerList;
		
		// @NotEmpty(message = "발령번호는 필수 값입니다.")
		private String ledgerId;
		
		private String listId;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(equalLedgerId(ledgerId))
				.and(equalListId(listId));
			
			
			return builder;
		}
		
		private BooleanExpression equalLedgerId(String ledgerId) {						
			return qType.ledger.ledgerId.eq(ledgerId);
		}
		
		private BooleanExpression equalListId(String listId) {
			if (StringUtils.isEmpty(listId)) {
				return null;
			}
			
			return qType.listId.eq(listId);
		}
	}
	
	@Data	
	@AllArgsConstructor
	@Builder
	public static class SaveLedgerList implements Serializable {
												
		private static final long serialVersionUID = -339266416839829125L;
		
		private String ledgerId;
		
		private String listId;
				
		private Long sequence;
			
		@NotEmpty
		private String empId;					
							
		private String appointmentCode;
		
		private LocalDate appointmentFromDate;
						
		private LocalDate appointmentToDate;		
		
		private List<ChangeInfo> changeInfoList = new ArrayList<>();
				
		public SaveLedgerList() {
			this.changeInfoList = new ArrayList<>();
		}		
		
		public LedgerList newEntity() {
			LedgerList entity = new LedgerList(this.empId
											  ,this.appointmentCode							  
											  ,this.appointmentFromDate
											  ,this.appointmentToDate);
						
			for (ChangeInfo info : changeInfoList ) {
				entity.addChangeInfo(this.newLedgerChangeInfo(entity, info));
			}
			
			return entity;
		}

				
		public LedgerList modifyEntity(LedgerList entity) {
			entity.modifyEntity(getAppointmentCode()
							   ,getAppointmentFromDate()
							   ,getAppointmentToDate()
							   ,null);
										
			for (ChangeInfo info : changeInfoList ) {
				if (info.getId() != null) {
					LedgerChangeInfo ledgerChangeInfo = entity.getChangeInfo(info.getId());  													
					ledgerChangeInfo.changeCode(info.getChangeCode()
											   ,info.getSequence());					
				} else {
					entity.addChangeInfo(this.newLedgerChangeInfo(entity, info));
				}
			}
			
			return entity;
		}
		
		public static SaveLedgerList convert(LedgerList entity) {
			
			List<ChangeInfo> list = new ArrayList<>();
			
			if (entity.getChangeInfoList() != null) {
				for (LedgerChangeInfo info : entity.getChangeInfoList()) {
					list.add(new ChangeInfo(info.getId()
										   ,info.getChangeType().toString()
										   ,info.getChangeTypeDetail()
										   ,info.getChangeCode()
										   ,info.getSequence()));
				}
			}
			
			return SaveLedgerList
					.builder()
					.ledgerId(entity.getLedger().getLedgerId())
					.listId(entity.getListId())
					.sequence(entity.getSequence())
					.appointmentCode(entity.getAppointmentCode())
					.empId(entity.getEmpId())
					.appointmentFromDate(entity.getAppointmentFromDate())
					.appointmentToDate(entity.getAppointmentToDate())
					.changeInfoList(list)
					.build();		
		}
		
		private LedgerChangeInfo newLedgerChangeInfo(LedgerList entity, ChangeInfo info) {
			return new LedgerChangeInfo(entity
					                   ,HrmTypeEnum.valueOf(info.getChangeType())
					                   ,info.getChangeTypeDetail()								                            
					                   ,info.getChangeCode()	
					                   ,info.getSequence());
		}
		
	}
	
	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)		
	public static class QueryLedgerList implements Serializable {	
				
		private static final long serialVersionUID = -999656657224379274L;

		private String ledgerId;
		
		private String listId;
				
		private Long sequence;
					
		private String empId;	
		
		private String empName;
							
		private String appointmentCode;
		
		private String appointmentCodeName;
		
		private LocalDate appointmentFromDate;
						
		private LocalDate appointmentToDate;
		
		private Boolean finishYn;

		/**
		 * @param ledgerId
		 * @param listId
		 * @param sequence
		 * @param empId
		 * @param empName
		 * @param appointmentCode
		 * @param appointmentCodeName
		 * @param appointmentFromDate
		 * @param appointmentToDate
		 * @param finishYn
		 */
		@QueryProjection
		public QueryLedgerList(String ledgerId, String listId, Long sequence, String empId, String empName,
				String appointmentCode, String appointmentCodeName, LocalDate appointmentFromDate,
				LocalDate appointmentToDate, Boolean finishYn) {
			this.ledgerId = ledgerId;
			this.listId = listId;
			this.sequence = sequence;
			this.empId = empId;
			this.empName = empName;
			this.appointmentCode = appointmentCode;
			this.appointmentCodeName = appointmentCodeName;
			this.appointmentFromDate = appointmentFromDate;
			this.appointmentToDate = appointmentToDate;
			this.finishYn = finishYn;
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
