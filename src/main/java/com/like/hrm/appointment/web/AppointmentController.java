package com.like.hrm.appointment.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.DeptTypeDTO;
import com.like.hrm.appointment.boundary.JobTypeDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetails;
import com.like.hrm.appointment.domain.model.DeptType;
import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AppointmentController {

	@Resource
	private AppointmentService appointmentService;	

	@RequestMapping(value={"/hrm/appointmentcode/{id}"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getCode(@PathVariable(value="id") String id) {
		
		AppointmentCode code = appointmentService.getAppointmentCode(id);
					
		return WebControllerUtil.getResponse(code
											,code == null ? 0 : 1
											,true
											,String.format("%d 건 조회되었습니다.", code == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/appointmentcode"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody AppointmentCodeDTO.CodeSave code, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveAppointmentCode(code);						
								 					
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}	
		
	@GetMapping("/hrm/depttype/{id}")
	public ResponseEntity<?> getDeptType(@PathVariable(value="id") String id) {
		
		DeptType code = appointmentService.getDeptType(id);
					
		return WebControllerUtil.getResponse(code, 
				code == null ? 0 : 1, 
				true, 
				String.format("%d 건 조회되었습니다.", code == null ? 0 : 1), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/depttype"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveDeptType(@RequestBody DeptTypeDTO.CodeSave code, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		//appointmentService.saveDeptType(code.getCommonCode());						
								 					
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	
	@GetMapping("/hrm/jobtype/{id}")
	public ResponseEntity<?> getJobType(@PathVariable(value="id") String id) {
		
		JobType code = appointmentService.getJobType(id);
					
		return WebControllerUtil.getResponse(code, 
				code == null ? 0 : 1, 
				true, 
				String.format("%d 건 조회되었습니다.", code == null ? 0 : 1), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/jobtype"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveJobType(@RequestBody JobTypeDTO.CodeSave code, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		//appointmentService.saveJobType(code.getCommonCode());						
								 					
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	
}
