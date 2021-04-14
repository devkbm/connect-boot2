package com.like.hrm.appointment.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.like.core.web.util.WebControllerUtil;
import com.like.hrm.appointment.boundary.AppointmentListDTO;
import com.like.hrm.appointment.boundary.QueryAppointmentList;
import com.like.hrm.appointment.service.AppointmentListQueryService;

public class AppointmentListQueryController {

	private AppointmentListQueryService queryService;
	
	public AppointmentListQueryController(AppointmentListQueryService queryService) {
		this.queryService = queryService;		
	}
	
	@GetMapping("/hrm/appointmentlist")
	public ResponseEntity<?> getList(AppointmentListDTO.SearchAppointmentList dto) {
				
		List<QueryAppointmentList> list = queryService.getListDTO(dto);						
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
}
