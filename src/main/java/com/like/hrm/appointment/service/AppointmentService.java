package com.like.hrm.appointment.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.DeptTypeDTO;
import com.like.hrm.appointment.boundary.JobTypeDTO;
import com.like.hrm.appointment.domain.event.ProcessEvent;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.DeptType;
import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.domain.model.enums.ChangeType;
import com.like.hrm.appointment.domain.repository.AppointmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AppointmentService {

	@Autowired
	public ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public void doSomething() {
		log.info("서비스 발행");
		applicationEventPublisher.publishEvent(new ProcessEvent(this, new Ledger()));
	}
	
	public AppointmentCode getAppointmentCode(String codeId) {
		return appointmentRepository.getAppointmentCode(codeId);
	}
	
	public void saveAppointmentCode(AppointmentCodeDTO.SaveCode dto) {
		AppointmentCode appointmentCode = appointmentRepository.getAppointmentCode(dto.getCode());
		
		if (appointmentCode == null ) {		
			appointmentCode = new AppointmentCode(dto.getCode()
												 ,dto.getCodeName()
												 ,true
												 ,dto.getSequence()
												 ,null);
		}
		
		appointmentRepository.saveAppintmentCode(appointmentCode);
	}
	
	public void deleteAppintmentCode(AppointmentCode appointmentCode) {
		appointmentRepository.deleteAppintmentCode(appointmentCode);
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
		
	public DeptType getDeptType(String code) {
		return appointmentRepository.getDeptType(ChangeType.DEPT.toString() + code);
	}
	
	public void saveDeptType(DeptTypeDTO.SaveCode dto) {
		DeptType deptType = appointmentRepository.getDeptType(ChangeType.DEPT.toString() + dto.getCode());
		
		if (deptType == null) {
			deptType = dto.newDeptType();
		} else {					
			deptType.changeInfo(dto.getCodeName()
					 		   ,dto.isUseYn()
					 		   ,dto.getSequence()
					 		   ,dto.getComment());
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

	public JobType getJobType(String code) {
		return appointmentRepository.getJobType(ChangeType.JOB.toString() + code);
	}

	public void saveJobType(JobTypeDTO.SaveCode dto) {
		JobType jobType = appointmentRepository.getJobType(ChangeType.JOB.toString() + dto.getCode());
		
		if (jobType == null) {
			jobType = dto.newJobType();
		} else {
			jobType.changeInfo(dto.getCode()
							  ,dto.isUseYn()
							  ,dto.getSequence()
							  ,dto.getComment());
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
