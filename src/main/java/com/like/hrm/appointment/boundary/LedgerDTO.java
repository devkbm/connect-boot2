package com.like.hrm.appointment.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerChangeInfo;
import com.like.hrm.appointment.domain.model.LedgerList;

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

		private String ledgerId;
		
		private String listId;
			
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
				entity.addChangeInfo(new LedgerChangeInfo(entity
						                                , info.getChangeType()
						                                , info.getChangeCode())	);
			}
			
			return entity;
		}

		
		
		public LedgerList modifyEntity(LedgerList entity) {
			entity.modifyEntity(getToDate());
						
			for (ChangeInfo info : changeInfoList ) {
				
				LedgerChangeInfo ledgerChangeInfo = entity.getChangeInfo(info.getId());  
								
				if (ledgerChangeInfo == null ) {
					entity.addChangeInfo(new LedgerChangeInfo(entity
								                            , info.getChangeType()
								                            , info.getChangeCode())	);
				} else {
					ledgerChangeInfo.changeCode(info.getChangeCode());
				}
			}
			
			return entity;
		}
		
	}
	
	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class ChangeInfo implements Serializable {
		
		private static final long serialVersionUID = -83012562627190252L;

		Long id;
		
		String changeType;
		
		String changeCode;
	}
	
	
}
