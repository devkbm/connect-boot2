package com.like.hrm.appointment.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.core.web.exception.ControllerException;
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
		
		AppointmentList list = repository.findById(listId).orElse(null);
		
		if (list.getFinishYn()) {
			throw new ControllerException("처리가 완료된 발령입니다.");
		}
			
		applicationEventPublisher.publishEvent(new AppointmentProcessEvent(this, list));
	}		
	
	public AppointmentList getAppointmentList(Long listId) {		
		AppointmentList list = repository.findById(listId).orElse(null);			
		
		return list;			
	}
	
	public void saveAppointmentList(AppointmentListDTO.SaveAppointmentList dto) {		
		AppointmentList list = dto.getListId() == null ? null : repository.findById(dto.getListId()).orElse(null);
		
		if (list == null) {			
			list = dto.newEntity();			
		} else {
			list = dto.modifyEntity(list);
		}	
		
		repository.save(list);
	}
	
	public void deleteAppointmentList(Long listId) {				
		repository.deleteById(listId);
	}	

}
