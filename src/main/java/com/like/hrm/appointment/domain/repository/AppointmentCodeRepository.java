package com.like.hrm.appointment.domain.repository;

import java.util.List;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.DeptType;
import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.domain.model.TypeDetailCode;

public interface AppointmentCodeRepository {

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
	
	TypeDetailCode getTypeDetailCode(String id);
	
	List<TypeDetailCode> getTypeDetailCodeList(String typeId);
	
	void saveTypeDetailCode(TypeDetailCode typeDetailCode);
	
	void deleteTypeDetailCode(TypeDetailCode typeDetailCode);
	
	/**
	 * 발령코드를 조회한다.
	 * @param codeId
	 * @return
	 */
	AppointmentCode getAppointmentCode(String codeId);
	
	/**
	 * 발령코드 명단을 조회한다.
	 * @param condition
	 * @return
	 */
	List<AppointmentCode> getAppointmentCodeList(AppointmentCodeDTO.SearchCode dto);
	
	/**
	 * 발령코드 상세정보 명단을 조회한다.
	 * @param dto
	 * @return
	 */
	List<AppointmentCodeDetail> getAppointmentCodeDetailList(AppointmentCodeDTO.SearchCodeDetail dto);
	
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
}
