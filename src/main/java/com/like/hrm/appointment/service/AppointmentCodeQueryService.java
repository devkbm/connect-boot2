package com.like.hrm.appointment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.commoncode.domain.repository.CommonCodeQueryRepository;
import com.like.hrm.appointment.boundary.AppointmentListDTO;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.ChangeableCodeDTO;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCodeDetail;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.repository.AppointmentCodeRepository;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;

@Service
@Transactional(readOnly = true)
public class AppointmentCodeQueryService {
	
	private AppointmentCodeRepository repository;
	
	private CommonCodeQueryRepository codeQueryRepository;
		
	public AppointmentCodeQueryService(AppointmentCodeRepository repository
			                          ,CommonCodeQueryRepository codeQueryRepository) {
		this.repository = repository;
		this.codeQueryRepository = codeQueryRepository;
	}	
	
	public List<AppointmentCode> getAppointentCodeList(AppointmentCodeDTO.SearchCode search) {
		return repository.getAppointmentCodeList(search);
	}
	
	public List<AppointmentCodeDetail> getAppointmentCodeDetailList(SearchCodeDetail dto) {
		return repository.getAppointmentCodeDetailList(dto);
	}
	
	public List<ChangeableCodeDTO.EnumDTO> getChangeableCodeDTO(HrmTypeEnum type) {
		return this.codeQueryRepository.getCodeList(type.getParentCommonCodeId())
									 .stream()
									 .map( r -> ChangeableCodeDTO.EnumDTO.builder().code(r.getCode()).name(r.getCodeName()).build())
									 .collect(Collectors.toList());
	}
	
	public List<AppointmentListDTO.ChangeInfo> getChangeInfoList(String appointmentCode) {
		List<AppointmentCodeDetail> list = new ArrayList<>(repository.getAppointmentCode(appointmentCode).getCodeDetails().values());		
		//log.info(list.toString());
		
		return AppointmentListDTO.ChangeInfo.convert(list);
		
	}
}
