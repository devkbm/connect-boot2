package com.like.hrm.appointment.domain.repository;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.DeptType;
import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.domain.model.Ledger;

@Repository
public interface AppointmentRepository {

		
	DeptType getDeptType(String id);
	
	void saveDeptType(DeptType deptType);
	
	void deleteDeptType(DeptType deptType);	
	
	JobType getJobType(String id);
	
	void saveJobType(JobType jobType);
	
	void deleteJobType(JobType jobType);	
	
	AppointmentCode getAppointmentCode(String codeId);
	
	void saveAppintmentCode(AppointmentCode appointmentCode);
	
	void deleteAppintmentCode(AppointmentCode appointmentCode);
	
	Ledger getLedger(String id);
	
	void saveLedger(Ledger ledger);
	
	void deleteLedger(Ledger ledger);
}
