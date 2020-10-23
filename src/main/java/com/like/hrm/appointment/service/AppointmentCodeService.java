package com.like.hrm.appointment.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
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
	
	public void deleteAppintmentCode(String codeId) {
		AppointmentCode appointmentCode = appointmentCodeRepository.getAppointmentCode(codeId);
		
		appointmentCodeRepository.deleteAppintmentCode(appointmentCode);
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
	
}
