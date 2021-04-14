package com.like.hrm.appointment.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.service.AppointmentCodeQueryService;

@RestController
public class AppointmentCodeQueryController {

	private AppointmentCodeQueryService queryService;
	
	public AppointmentCodeQueryController(AppointmentCodeQueryService queryService) {
		this.queryService = queryService;	
	}
	
	@GetMapping("/hrm/appointmentcode")
	public ResponseEntity<?> getCodeList(AppointmentCodeDTO.SearchCode search) {
																	
		List<AppointmentCode> list = queryService.getAppointentCodeList(search);  							
		
		List<AppointmentCodeDTO.SaveCode> dtoList = list.stream()
														.map(r -> AppointmentCodeDTO.SaveCode.convertDTO(r))
														.collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/appointmentcodedetail")
	public ResponseEntity<?> getCodeDetailList(AppointmentCodeDTO.SearchCodeDetail dto) {
		 				
		List<AppointmentCodeDetail> list = queryService.getAppointmentCodeDetailList(dto);

		List<AppointmentCodeDTO.SaveCodeDetail> dtoList = list.stream()
															  .map(r -> AppointmentCodeDTO.SaveCodeDetail.convert(r))
															  .collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
}
