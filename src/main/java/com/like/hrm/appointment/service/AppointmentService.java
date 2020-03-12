package com.like.hrm.appointment.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.common.web.exception.ControllerException;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCodeDetail;
import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.domain.event.AppointmentProcessEvent;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.repository.AppointmentLedgerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AppointmentService {

	public ApplicationEventPublisher applicationEventPublisher;
	
	private AppointmentLedgerRepository appointmentRepository;
	
	public AppointmentService(ApplicationEventPublisher applicationEventPublisher
							 ,AppointmentLedgerRepository appointmentRepository) {
		this.applicationEventPublisher = applicationEventPublisher;
		this.appointmentRepository = appointmentRepository;
	}

	public void appoint(String ledgerId, String listId) {
		//log.info("서비스 발행");
		Ledger ledger = appointmentRepository.getLedger(ledgerId);
		LedgerList list = ledger.getAppointmentList(listId);
		
		if (list.getFinishYn()) {
			throw new ControllerException("처리가 완료된 발령입니다.");
		}
			
		applicationEventPublisher.publishEvent(new AppointmentProcessEvent(this, list));
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
	
	public LedgerList getLedgerList(String ledgerId, String listId) {
		Ledger ledger = appointmentRepository.getLedger(ledgerId);
		LedgerList list = ledger.getAppointmentList(listId);			
		
		return list;			
	}
	
	public void saveLedgerList(LedgerDTO.SaveLedgerList dto) {
		Ledger ledger = appointmentRepository.getLedger(dto.getLedgerId());
		LedgerList list = ledger.getAppointmentList(dto.getListId());			
		
		if (list == null) {			
			list = dto.newEntity(ledger);
			ledger.addAppointmentList(list);
		} else {
			list = dto.modifyEntity(list);
		}			
	}
	
	public void deleteLedgerList(String ledgerId, String listId) {
		Ledger ledger = appointmentRepository.getLedger(ledgerId);
		
		ledger.deleteAppointmentList(listId);
	}	

}
