package com.like.hrm.appointment.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerChangeInfo;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.model.enums.ChangeType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LedgerDTO {
	
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
	public static class ChangeInfo implements Serializable {
		
		private static final long serialVersionUID = -83012562627190252L;

		Long id;
		
		String changeType;
		
		String changeTypeDetail;
		
		String changeCode;
		
		Integer sequence;
	}
	
	
}
