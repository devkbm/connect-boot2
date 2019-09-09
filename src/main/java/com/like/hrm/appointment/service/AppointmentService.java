package com.like.hrm.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.domain.event.ProcessEvent;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.AppointmentLedger;
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
		applicationEventPublisher.publishEvent(new ProcessEvent(this, new AppointmentLedger()));
	}
	
	public AppointmentCode getAppointmentCode(String codeId) {
		return appointmentRepository.getAppointmentCode(codeId);
	}
	
	public void saveAppointmentCode(AppointmentCodeDTO.CodeSave dto) {
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
		AppointmentCodeDetail appointmentCodeDetail = appointmentRepository.getAppointmentCodeDetail(dto.getPkCodeDetails());
		
		if (appointmentCode == null)
			throw new IllegalArgumentException(dto.getCode() + " 엔티티가 존재하지 않습니다.");
		
		if (appointmentCodeDetail == null) {
			appointmentCodeDetail = dto.newAppointmentCodeDetail(appointmentCode);
		} else {
			
		}
						
		appointmentCode.addAppointmentCodeDetail(appointmentCodeDetail);
	}
		
	public DeptType getDeptType(String id) {
		return appointmentRepository.getDeptType(id);
	}
	
	public void saveDeptType(DeptType deptType) {
		appointmentRepository.saveDeptType(deptType);		
	}

	public void deleteDeptType(DeptType deptType) {
		appointmentRepository.deleteDeptType(deptType);		
	}

	public JobType getJobType(String id) {
		return appointmentRepository.getJobType(id);
	}

	public void saveJobType(JobType jobType) {
		appointmentRepository.saveJobType(jobType);		
	}

	public void deleteJobType(JobType jobType) {
		appointmentRepository.deleteJobType(jobType);		
	}


}
