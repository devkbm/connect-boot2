package com.like.hrm.appointment.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.hrm.appointment.boundary.AppointmentRegisterDTO;
import com.like.hrm.appointment.domain.model.AppointmentRegister;
import com.like.hrm.appointment.service.AppointmentRegisterQueryService;

@RestController
public class AppointmentRegisterQueryController {

	private AppointmentRegisterQueryService queryService;
	
	public AppointmentRegisterQueryController(AppointmentRegisterQueryService queryService) {
		this.queryService = queryService;
	}
	
	@GetMapping("/hrm/appointmentregister")
	public ResponseEntity<?> getAppointmentRegister(AppointmentRegisterDTO.SearchAppointmentRegister dto) {
		
		List<AppointmentRegister> list = queryService.getLedger(dto);						
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
}
