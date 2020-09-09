package com.like.hrm.appointment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.commoncode.infra.jparepository.CodeJpaRepository;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.ChangeableCodeDTO;
import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCodeDetail;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.repository.AppointmentCodeRepository;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;

@Service
@Transactional(readOnly = true)
public class AppointmentCodeQueryService {
	
	private AppointmentCodeRepository appointmentQueryRepository;
	
	private CodeJpaRepository codeJpaRepository;
	
	@Autowired
	public AppointmentCodeQueryService(AppointmentCodeRepository appointmentQueryRepository
			                          ,CodeJpaRepository codeJpaRepository) {
		this.appointmentQueryRepository = appointmentQueryRepository;
		this.codeJpaRepository = codeJpaRepository;
	}	
	
	public List<AppointmentCode> getAppointentCodeList(AppointmentCodeDTO.SearchCode search) {
		return appointmentQueryRepository.getAppointmentCodeList(search);
	}
	
	public List<AppointmentCodeDetail> getAppointmentCodeDetailList(SearchCodeDetail dto) {
		return appointmentQueryRepository.getAppointmentCodeDetailList(dto);
	}
	
	public List<ChangeableCodeDTO.EnumDTO> getChangeableCodeDTO(HrmTypeEnum type) {
		return this.codeJpaRepository.getCodeList(type.getParentCommonCodeId())
									 .stream()
									 .map( r -> ChangeableCodeDTO.EnumDTO.builder().code(r.getCode()).name(r.getCodeName()).build())
									 .collect(Collectors.toList());
	}
	
	public List<LedgerDTO.ChangeInfo> getChangeInfoList(String appointmentCode) {
		List<AppointmentCodeDetail> list = new ArrayList<>(appointmentQueryRepository.getAppointmentCode(appointmentCode).getCodeDetails().values());		
		//log.info(list.toString());
		
		return LedgerDTO.ChangeInfo.convert(list);
	}
}
