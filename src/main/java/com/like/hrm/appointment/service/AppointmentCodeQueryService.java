package com.like.hrm.appointment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.commoncode.infra.jparepository.CodeJpaRepository;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.ChangeableCodeDTO;
import com.like.hrm.appointment.boundary.ChangeableTypeDTO;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCodeDetail;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.enums.ChangeType;
import com.like.hrm.appointment.domain.repository.AppointmentQueryRepository;

@Service
@Transactional(readOnly = true)
public class AppointmentCodeQueryService {
	
	private AppointmentQueryRepository appointmentQueryRepository;
	
	private CodeJpaRepository codeJpaRepository;
	
	@Autowired
	public AppointmentCodeQueryService(AppointmentQueryRepository appointmentQueryRepository
			                          ,CodeJpaRepository codeJpaRepository) {
		this.appointmentQueryRepository = appointmentQueryRepository;
		this.codeJpaRepository = codeJpaRepository;
	}
	
	public AppointmentCodeQueryService(AppointmentQueryRepository appointmentQueryRepository) {		
		this.appointmentQueryRepository = appointmentQueryRepository;
	}
	
	public List<AppointmentCode> getAppointentCodeList(AppointmentCodeDTO.SearchCode search) {
		return appointmentQueryRepository.getAppointmentCodeList(search);
	}
	
	public List<AppointmentCodeDetail> getAppointmentCodeDetailList(SearchCodeDetail dto) {
		return appointmentQueryRepository.getAppointmentCodeDetailList(dto);
	}
	
	public List<ChangeableCodeDTO.EnumDTO> getChangeableCodeDTO(ChangeType type) {
		return this.codeJpaRepository.getCodeList(type.getParentCommonCodeId())
									 .stream()
									 .map( r -> ChangeableCodeDTO.EnumDTO.builder().code(r.getCode()).name(r.getCodeName()).build())
									 .collect(Collectors.toList());
	}
}
