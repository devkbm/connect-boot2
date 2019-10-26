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
import com.like.hrm.appointment.domain.model.QLedgerList;
import com.like.hrm.appointment.domain.model.enums.ChangeType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

public class LedgerDTO {
	
	public static List<ChangeInfo> convertDTO(List<AppointmentCodeDetail> detailList) {
		
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
	
	public static SaveLedgerList convertDTO(LedgerList entity) {
		
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
				.fromDate(entity.getAppointmentFromDate())
				.toDate(entity.getAppointmentToDate())
				.changeInfoList(list)
				.build();		
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
		
		@NotEmpty(message = "발령번호는 필수 값입니다.")
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

		@NotEmpty
		private String ledgerId;
		
		private String listId;
				
		private Long sequence;
			
		@NotEmpty
		private String empId;					
							
		private String appointmentCode;
		
		private LocalDate fromDate;
						
		private LocalDate toDate;		
		
		private List<ChangeInfo> changeInfoList = new ArrayList<>();
				
		public SaveLedgerList() {
			this.changeInfoList = new ArrayList<>();
		}		
		
		public LedgerList newEntity(Ledger ledger) {
			LedgerList entity = new LedgerList(ledger
											  ,this.empId
											  ,this.appointmentCode							  
											  ,this.fromDate
											  ,this.toDate);
						
			for (ChangeInfo info : changeInfoList ) {
				entity.addChangeInfo(this.newLedgerChangeInfo(entity, info));
			}
			
			return entity;
		}

				
		public LedgerList modifyEntity(LedgerList entity) {
			entity.modifyEntity(getToDate());
						
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
		
		private LedgerChangeInfo newLedgerChangeInfo(LedgerList entity, ChangeInfo info) {
			return new LedgerChangeInfo(entity
					                   ,ChangeType.valueOf(info.getChangeType())
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
	}
	
	
}
