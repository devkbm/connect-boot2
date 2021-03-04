package com.like.hrm.appointment.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.common.web.exception.ControllerException;
import com.like.hrm.appointment.boundary.AppointmentListDTO;
import com.like.hrm.appointment.domain.event.AppointmentProcessEvent;
import com.like.hrm.appointment.domain.model.AppointmentList;
import com.like.hrm.appointment.domain.repository.AppointmentListRepository;

@Service
@Transactional
public class AppointmentListCommandService {

	public ApplicationEventPublisher applicationEventPublisher;
	
	private AppointmentListRepository repository;
	
	public AppointmentListCommandService(ApplicationEventPublisher applicationEventPublisher
										,AppointmentListRepository repositoryy) {
		this.applicationEventPublisher = applicationEventPublisher;
		this.repository = repositoryy;
	}

	public void appoint(Long listId) {
		//log.info("서비스 발행");
		
		AppointmentList list = repository.get(listId);
		
		if (list.getFinishYn()) {
			throw new ControllerException("처리가 완료된 발령입니다.");
		}
			
		applicationEventPublisher.publishEvent(new AppointmentProcessEvent(this, list));
	}		
	
	public AppointmentList getAppointmentList(Long listId) {		
		AppointmentList list = repository.get(listId);			
		
		return list;			
	}
	
	public void saveAppointmentList(AppointmentListDTO.SaveAppointmentList dto) {		
		AppointmentList list = repository.get(dto.getListId());			
		
		if (list == null) {			
			list = dto.newEntity();			
		} else {
			list = dto.modifyEntity(list);
		}			
	}
	
	public void deleteAppointmentList(Long listId) {
		AppointmentList list = repository.get(listId);
		
		repository.delete(list);
	}	

}
