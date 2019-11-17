package com.like.hrm.appointment.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.DeptType;
import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;

@Repository
public interface AppointmentRepository {

	/**
	 * 부서 유형을 조회한다.
	 * @return
	 */
	List<DeptType> getDeptTypeList();
	
	/**
	 * 부서 유형을 조회한다.
	 * @param id
	 * @return
	 */
	DeptType getDeptType(String id);
	
	/**
	 * 부서 유형을 저장한다.
	 * @param deptType
	 */
	void saveDeptType(DeptType deptType);
	
	/**
	 * 부서 유형을 삭제한다.
	 * @param deptType
	 */
	void deleteDeptType(DeptType deptType);	
	
	
	List<JobType> getJobTypeList();
	
	/**
	 * 인사 유형을 조회한다.
	 * @param id
	 * @return
	 */
	JobType getJobType(String id);
	
	/**
	 * 인사 유형을 저장한다.
	 * @param jobType
	 */
	void saveJobType(JobType jobType);
	
	/**
	 * 인사 유형을 삭제한다.
	 * @param jobType
	 */
	void deleteJobType(JobType jobType);	
	
	/**
	 * 발령코드를 조회한다.
	 * @param codeId
	 * @return
	 */
	AppointmentCode getAppointmentCode(String codeId);
	
	/**
	 * 발령코드를 저장한다.
	 * @param appointmentCode
	 */
	void saveAppintmentCode(AppointmentCode appointmentCode);
	
	/**
	 * 발령코드를 삭제한다.
	 * @param appointmentCode
	 */
	void deleteAppintmentCode(AppointmentCode appointmentCode);	
		
	Ledger getLedger(String id);
	
	void saveLedger(Ledger ledger);
	
	void deleteLedger(Ledger ledger);	
	
}
