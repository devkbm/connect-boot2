package com.like.hrm.appointment.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentRegisterDTO;
import com.like.hrm.appointment.domain.model.AppointmentRegister;
import com.like.hrm.appointment.domain.repository.AppointmentRegisterRepository;

@Service
@Transactional
public class AppointmentRegisterCommandService {

	private AppointmentRegisterRepository repository;
	
	public AppointmentRegisterCommandService(AppointmentRegisterRepository repository) {
		this.repository = repository;
	}
	
	public AppointmentRegister getLedger(String id) {
		return repository.get(id);
	}
	
	public void saveLedger(AppointmentRegisterDTO.SaveAppointmentRegister dto) {
		AppointmentRegister ledger = repository.get(dto.getLedgerId());
		
		if (ledger == null) {
			ledger = dto.newEntity();
		} else {
			dto.modifyEntity(ledger);
		}
		
		repository.save(ledger);
	}
	
	public void deleteLedger(String id) {
		AppointmentRegister ledger = repository.get(id);
		
		if (ledger == null) {
			throw new EntityNotFoundException(id + " 엔티티가 존재하지 않습니다.");
		} 
		
		repository.delete(ledger);
	}
}
