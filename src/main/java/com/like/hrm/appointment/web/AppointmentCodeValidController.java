package com.like.hrm.appointment.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.repository.AppointmentCodeRepository;

@RestController
public class AppointmentCodeValidController {

	private AppointmentCodeRepository appointmentCodeRepository;
	
	public AppointmentCodeValidController(AppointmentCodeRepository appointmentCodeRepository) {
		this.appointmentCodeRepository = appointmentCodeRepository;		
	}
	
	@GetMapping("/hrm/appointmentcode/{id}/valid")
	public ResponseEntity<?> getCode(@PathVariable(value="id") String id) {
		
		boolean exist = appointmentCodeRepository.existsById(id);
					
		return WebControllerUtil.getResponse(exist											
											,exist == true ? "사용가능한 발령 코드입니다." : "기존 발령 코드가 존재합니다."
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/appointmentcodedetail/{id}/{detailId}/valid")
	public ResponseEntity<?> getCodeDetail(@PathVariable(value="id") String id,
										   @PathVariable(value="detailId") String detailId) {
		 		
		AppointmentCode code = appointmentCodeRepository.findById(id).orElse(null);
										
		boolean exist = code.getCodeDetail(detailId) == null ? false : true;
		
		return WebControllerUtil.getResponse(exist										
											,exist == true ? "사용가능한 발령 코드입니다." : "기존 발령 코드가 존재합니다."
											,HttpStatus.OK);
	}
}
