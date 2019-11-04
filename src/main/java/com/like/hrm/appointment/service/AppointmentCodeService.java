package com.like.hrm.appointment.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.DeptTypeDTO;
import com.like.hrm.appointment.boundary.JobTypeDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.DeptType;
import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.domain.model.enums.ChangeType;
import com.like.hrm.appointment.domain.repository.AppointmentRepository;

@Service
@Transactional
public class AppointmentCodeService {

	private AppointmentRepository appointmentRepository;
	
	public AppointmentCodeService(AppointmentRepository appointmentRepository) {		
		this.appointmentRepository = appointmentRepository;
	}
	
	public AppointmentCode getAppointmentCode(String codeId) {
		return appointmentRepository.getAppointmentCode(codeId);
	}
	
	public void saveAppointmentCode(AppointmentCodeDTO.SaveCode dto) {
		AppointmentCode appointmentCode = appointmentRepository.getAppointmentCode(dto.getCode());
		
		if (appointmentCode == null ) {		
			appointmentCode = dto.newAppointmentCode();
		} else {
			dto.modifyEntity(appointmentCode);
		}
		
		appointmentRepository.saveAppintmentCode(appointmentCode);
	}
	
	public void deleteAppintmentCode(AppointmentCode appointmentCode) {
		appointmentRepository.deleteAppintmentCode(appointmentCode);
	}	
	
	public AppointmentCodeDetail getAppointmentCodeDetail(String appointmentCode, String type) {
		AppointmentCode entity = appointmentRepository.getAppointmentCode(appointmentCode);
		
		return entity.getCodeDetail(type);
	}
		
	public void saveAppointmentCodeDetail(AppointmentCodeDTO.SaveCodeDetail dto) {
		AppointmentCode appointmentCode = appointmentRepository.getAppointmentCode(dto.getCode());			
		
		if (appointmentCode == null) {
			throw new EntityNotFoundException(dto.getCode() + " 엔티티가 존재하지 않습니다.");
		}
		
		AppointmentCodeDetail appointmentCodeDetail = appointmentCode.getCodeDetail(dto.getDetailId());						
		
		if (appointmentCodeDetail == null) {
			appointmentCodeDetail = dto.newAppointmentCodeDetail(appointmentCode);
			appointmentCode.addAppointmentCodeDetail(appointmentCodeDetail);
		} else {
			appointmentCodeDetail.setSequence(dto.getSequence());
		}						
		
	}
	
	public void deleteAppointmentCodeDetail(AppointmentCodeDTO.SaveCodeDetail dto) {
		AppointmentCode appointmentCode = appointmentRepository.getAppointmentCode(dto.getCode());			
		appointmentCode.deleteAppointmentCodeDetail(dto.getDetailId());			
	}
		
	public List<DeptType> getDeptTypeList() {
		return appointmentRepository.getDeptTypeList();
	}
	
	public DeptType getDeptType(String code) {
		return appointmentRepository.getDeptType(ChangeType.DEPT.toString() + code);
	}
	
	public DeptTypeDTO.SaveCode getDeptTypeDTO(String code) {
		DeptType entity = this.getDeptType(code);
		
		return DeptTypeDTO.SaveCode.convert(entity);
	}
	
	public void saveDeptType(DeptTypeDTO.SaveCode dto) {
		DeptType deptType = appointmentRepository.getDeptType(ChangeType.DEPT.toString() + dto.getCode());
		
		if (deptType == null) {
			deptType = dto.newDeptType();
		} else {					
			deptType = dto.changeInfo(deptType);
		}
		
		appointmentRepository.saveDeptType(deptType);		
	}

	public void deleteDeptType(String code) {
		DeptType deptType = appointmentRepository.getDeptType(ChangeType.DEPT.toString() + code);
		
		if (deptType == null) {
			throw new EntityNotFoundException(code + " 엔티티가 존재하지 않습니다.");
		}
		
		appointmentRepository.deleteDeptType(deptType);					
	}

	public List<JobType> getJobTypeList() {
		return appointmentRepository.getJobTypeList();
	}
	
	public JobType getJobType(String code) {
		return appointmentRepository.getJobType(ChangeType.JOB.toString() + code);
	}
	
	public JobTypeDTO.SaveCode getJobTypeDTO(String code) {
		JobType entity = appointmentRepository.getJobType(ChangeType.JOB.toString() + code);				
		
		return JobTypeDTO.SaveCode.convert(entity);
	}

	public void saveJobType(JobTypeDTO.SaveCode dto) {
		JobType jobType = appointmentRepository.getJobType(ChangeType.JOB.toString() + dto.getCode());
		
		if (jobType == null) {
			jobType = dto.newJobType();
		} else {
			jobType  = dto.changeInfo(jobType);
		}
		
		appointmentRepository.saveJobType(jobType);		
	}

	public void deleteJobType(String code) {
		JobType jobType = appointmentRepository.getJobType(ChangeType.JOB.toString() + code);
		
		if (jobType == null) {
			throw new EntityNotFoundException(code + " 엔티티가 존재하지 않습니다.");
		}
		
		appointmentRepository.deleteJobType(jobType);		
	}
}
