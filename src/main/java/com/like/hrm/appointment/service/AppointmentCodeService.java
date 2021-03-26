package com.like.hrm.appointment.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.AppointmentListDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.repository.AppointmentCodeRepository;

@Service
@Transactional
public class AppointmentCodeService {

	private AppointmentCodeRepository repository;
		
	public AppointmentCodeService(AppointmentCodeRepository repository) {		
		this.repository = repository;
	}
	
	public AppointmentCode getAppointmentCode(String codeId) {
		return repository.findById(codeId).orElse(null);
	}
	
	public void saveAppointmentCode(AppointmentCodeDTO.SaveCode dto) {
		AppointmentCode appointmentCode = repository.findById(dto.getCode()).orElse(null);
		
		if (appointmentCode == null ) {		
			appointmentCode = dto.newAppointmentCode();
		} else {
			dto.modifyEntity(appointmentCode);
		}
		
		repository.save(appointmentCode);
	}
	
	public void deleteAppintmentCode(String codeId) {				
		repository.deleteById(codeId);
	}
	
	public void deleteAppintmentCode(AppointmentCode appointmentCode) {
		repository.delete(appointmentCode);
	}	
	
	public AppointmentCodeDetail getAppointmentCodeDetail(String appointmentCode, String typeId) {
		AppointmentCode entity = repository.findById(appointmentCode).orElse(null);
		
		return entity.getCodeDetail(typeId);
	}
		
	public void saveAppointmentCodeDetail(AppointmentCodeDTO.SaveCodeDetail dto) {
		AppointmentCode appointmentCode = repository.findById(dto.getCode()).orElse(null);
		
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
		AppointmentCode entity = repository.findById(appointmentCode).orElse(null);			
		entity.deleteAppointmentCodeDetail(typeId);			
	}		
	
	public List<AppointmentListDTO.ChangeInfo> getChangeInfoList(String appointmentCode) {
		List<AppointmentCodeDetail> list = new ArrayList<>(repository.findById(appointmentCode).orElse(null).getCodeDetails().values());		
		//log.info(list.toString());
		
		return AppointmentListDTO.ChangeInfo.convert(list);
		
	}
}
