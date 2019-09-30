package com.like.hrm.appointment.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.DeptTypeDTO;
import com.like.hrm.appointment.boundary.JobTypeDTO;
import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.domain.event.ProcessEvent;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;
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
		//applicationEventPublisher.publishEvent(new ProcessEvent(this, new Ledger()));
	}
	
	public List<AppointmentCode> getAppointentCodeList(AppointmentCodeDTO.CodeSearch search) {
		return appointmentRepository.getAppointmentCodeList(search.getBooleanBuilder());
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
	
	public Ledger getLedger(String id) {
		return appointmentRepository.getLedger(id);
	}
	
	public void saveLedger(LedgerDTO.SaveLedger dto) {
		Ledger ledger = appointmentRepository.getLedger(dto.getLedgerId());
		
		if (ledger == null) {
			ledger = dto.newEntity();
		} else {
			dto.modifyEntity(ledger);
		}
		
		appointmentRepository.saveLedger(ledger);
	}
	
	public void deleteLedger(String id) {
		Ledger ledger = appointmentRepository.getLedger(id);
		
		if (ledger == null) {
			throw new EntityNotFoundException(id + " 엔티티가 존재하지 않습니다.");
		} 
		
		appointmentRepository.deleteLedger(ledger);
	}
	
	public void saveLedgerList(LedgerDTO.SaveLedgerList dto) {
		Ledger ledger = appointmentRepository.getLedger(dto.getLedgerId());
		LedgerList list = ledger.getAppointmentList(dto.getListId());
		
		log.info(dto.toString());
		
		if (list == null) {			
			list = dto.newEntity(ledger);
			ledger.addAppointmentList(list);
		} else {
			list = dto.modifyEntity(list);
		}			
	}

}
