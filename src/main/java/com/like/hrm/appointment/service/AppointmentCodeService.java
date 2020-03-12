package com.like.hrm.appointment.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.DeptTypeDTO;
import com.like.hrm.appointment.boundary.JobTypeDTO;
import com.like.hrm.appointment.boundary.TypeDetailCodeDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.DeptType;
import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.domain.model.TypeDetailCode;
import com.like.hrm.appointment.domain.model.enums.ChangeType;
import com.like.hrm.appointment.domain.repository.AppointmentCodeRepository;

@Service
@Transactional
public class AppointmentCodeService {

	private AppointmentCodeRepository appointmentCodeRepository;
		
	public AppointmentCodeService(AppointmentCodeRepository appointmentCodeRepository) {		
		this.appointmentCodeRepository = appointmentCodeRepository;
	}
	
	public AppointmentCode getAppointmentCode(String codeId) {
		return appointmentCodeRepository.getAppointmentCode(codeId);
	}
	
	public void saveAppointmentCode(AppointmentCodeDTO.SaveCode dto) {
		AppointmentCode appointmentCode = appointmentCodeRepository.getAppointmentCode(dto.getCode());
		
		if (appointmentCode == null ) {		
			appointmentCode = dto.newAppointmentCode();
		} else {
			dto.modifyEntity(appointmentCode);
		}
		
		appointmentCodeRepository.saveAppintmentCode(appointmentCode);
	}
	
	public void deleteAppintmentCode(AppointmentCode appointmentCode) {
		appointmentCodeRepository.deleteAppintmentCode(appointmentCode);
	}	
	
	public AppointmentCodeDetail getAppointmentCodeDetail(String appointmentCode, String typeId) {
		AppointmentCode entity = appointmentCodeRepository.getAppointmentCode(appointmentCode);
		
		return entity.getCodeDetail(typeId);
	}
		
	public void saveAppointmentCodeDetail(AppointmentCodeDTO.SaveCodeDetail dto) {
		AppointmentCode appointmentCode = appointmentCodeRepository.getAppointmentCode(dto.getCode());			
		
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
	
	public void deleteAppointmentCodeDetail(String appointmentCode, String typeId) {
		AppointmentCode entity = appointmentCodeRepository.getAppointmentCode(appointmentCode);			
		entity.deleteAppointmentCodeDetail(typeId);			
	}
		
	public List<DeptType> getDeptTypeList() {
		return appointmentCodeRepository.getDeptTypeList();
	}
	
	public DeptType getDeptType(String code) {
		return appointmentCodeRepository.getDeptType(ChangeType.DEPT.toString() + code);
	}
	
	public DeptTypeDTO.SaveCode getDeptTypeDTO(String code) {
		DeptType entity = this.getDeptType(code);
		
		return DeptTypeDTO.SaveCode.convert(entity);
	}
	
	public void saveDeptType(DeptTypeDTO.SaveCode dto) {
		DeptType deptType = appointmentCodeRepository.getDeptType(ChangeType.DEPT.toString() + dto.getCode());
		
		if (deptType == null) {
			deptType = dto.newDeptType();
		} else {					
			deptType = dto.changeInfo(deptType);
		}
		
		appointmentCodeRepository.saveDeptType(deptType);		
	}

	public void deleteDeptType(String code) {
		DeptType deptType = appointmentCodeRepository.getDeptType(ChangeType.DEPT.toString() + code);
		
		if (deptType == null) {
			throw new EntityNotFoundException(code + " 엔티티가 존재하지 않습니다.");
		}
		
		appointmentCodeRepository.deleteDeptType(deptType);					
	}

	public List<JobType> getJobTypeList() {
		return appointmentCodeRepository.getJobTypeList();
	}
	
	public JobType getJobType(String code) {
		return appointmentCodeRepository.getJobType(ChangeType.JOB.toString() + code);
	}
	
	public JobTypeDTO.SaveCode getJobTypeDTO(String code) {
		JobType entity = appointmentCodeRepository.getJobType(ChangeType.JOB.toString() + code);				
		
		return JobTypeDTO.SaveCode.convert(entity);
	}

	public void saveJobType(JobTypeDTO.SaveCode dto) {
		JobType jobType = appointmentCodeRepository.getJobType(ChangeType.JOB.toString() + dto.getCode());
		
		if (jobType == null) {
			jobType = dto.newJobType();
		} else {
			jobType  = dto.changeInfo(jobType);
		}
		
		appointmentCodeRepository.saveJobType(jobType);		
	}

	public void deleteJobType(String code) {
		JobType jobType = appointmentCodeRepository.getJobType(ChangeType.JOB.toString() + code);
		
		if (jobType == null) {
			throw new EntityNotFoundException(code + " 엔티티가 존재하지 않습니다.");
		}
		
		appointmentCodeRepository.deleteJobType(jobType);		
	}
	
	public TypeDetailCode getTypeDetailCode(String id) {
		return appointmentCodeRepository.getTypeDetailCode(id);
	}
	
	public TypeDetailCodeDTO.SaveCode getTypeDetailCodeDTO(String id) {
		TypeDetailCode entity = appointmentCodeRepository.getTypeDetailCode(id);				
		
		return TypeDetailCodeDTO.SaveCode.convert(entity);
	}
	
	public void saveTypeDetailCode(TypeDetailCodeDTO.SaveCode dto) {
		TypeDetailCode typeDetailCode = appointmentCodeRepository.getTypeDetailCode(dto.getId());
		
		if (typeDetailCode == null) {
			typeDetailCode = dto.newTypeDetailCode();
		} else {
			typeDetailCode = dto.changeInfo(typeDetailCode);
		}
		
		appointmentCodeRepository.saveTypeDetailCode(typeDetailCode);
	}
	
	public void deleteTypeDetailCode(String id) {
		TypeDetailCode typeDetailCode = appointmentCodeRepository.getTypeDetailCode(id);
		
		if (typeDetailCode == null) {
			throw new EntityNotFoundException(id + " 엔티티가 존재하지 않습니다.");
		}
		
		appointmentCodeRepository.deleteTypeDetailCode(typeDetailCode);
	}
	
}
