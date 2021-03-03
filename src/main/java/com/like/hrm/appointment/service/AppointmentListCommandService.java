package com.like.hrm.appointment.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.common.web.exception.ControllerException;
import com.like.hrm.appointment.boundary.AppointmentListDTO;
import com.like.hrm.appointment.domain.event.AppointmentProcessEvent;
import com.like.hrm.appointment.domain.model.AppointmentRegister;
import com.like.hrm.appointment.domain.model.AppointmentList;
import com.like.hrm.appointment.domain.repository.AppointmentRegisterRepository;

@Service
@Transactional
public class AppointmentListCommandService {

	public ApplicationEventPublisher applicationEventPublisher;
	
	private AppointmentRegisterRepository appointmentRepository;
	
	public AppointmentListCommandService(ApplicationEventPublisher applicationEventPublisher
							 ,AppointmentRegisterRepository appointmentRepository) {
		this.applicationEventPublisher = applicationEventPublisher;
		this.appointmentRepository = appointmentRepository;
	}

	public void appoint(String ledgerId, String listId) {
		//log.info("서비스 발행");
		AppointmentRegister ledger = appointmentRepository.get(ledgerId);
		AppointmentList list = ledger.getAppointmentList(listId);
		
		if (list.getFinishYn()) {
			throw new ControllerException("처리가 완료된 발령입니다.");
		}
			
		applicationEventPublisher.publishEvent(new AppointmentProcessEvent(this, list));
	}		
	
	public AppointmentList getLedgerList(String ledgerId, String listId) {
		AppointmentRegister ledger = appointmentRepository.get(ledgerId);
		AppointmentList list = ledger.getAppointmentList(listId);			
		
		return list;			
	}
	
	public void saveLedgerList(AppointmentListDTO.SaveAppointmentList dto) {		
		AppointmentList list = null; //ledger.getAppointmentList(dto.getListId());			
		
		if (list == null) {			
			list = dto.newEntity();			
		} else {
			list = dto.modifyEntity(list);
		}			
	}
	
	public void deleteLedgerList(String ledgerId, String listId) {
		AppointmentRegister ledger = appointmentRepository.get(ledgerId);
		
		ledger.deleteAppointmentList(listId);
	}	

}
