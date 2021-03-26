package com.like.hrm.appointment.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.service.AppointmentCodeService;

@RestController
public class AppointmentCodeController {

	private AppointmentCodeService commandService;		

	public AppointmentCodeController(AppointmentCodeService commandService) {
		this.commandService = commandService;		
	}		
		
	@GetMapping("/hrm/appointmentcode/{id}")
	public ResponseEntity<?> getCode(@PathVariable(value="id") String id) {
		
		AppointmentCode code = commandService.getAppointmentCode(id);
					
		return WebControllerUtil.getResponse(code											
											,String.format("%d 건 조회되었습니다.", code == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/appointmentcode"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody AppointmentCodeDTO.SaveCode code, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		commandService.saveAppointmentCode(code);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/appointmentcode/{id}")
	public ResponseEntity<?> delCode(@PathVariable(value="id") String id) {						
												
		commandService.deleteAppintmentCode(id);
								 						
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}		
	
	@GetMapping("/hrm/appointmentcodedetail/{id}/{detailId}")
	public ResponseEntity<?> getCodeDetail(@PathVariable(value="id") String id,
										   @PathVariable(value="detailId") String detailId) {
		 		
		AppointmentCodeDetail code = commandService.getAppointmentCodeDetail(id, detailId);
					
		AppointmentCodeDTO.SaveCodeDetail dto = AppointmentCodeDTO.SaveCodeDetail.convert(code);
				
		return WebControllerUtil.getResponse(dto										
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/appointmentcodedetail"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCodeDetail(@RequestBody AppointmentCodeDTO.SaveCodeDetail dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		commandService.saveAppointmentCodeDetail(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/appointmentcodedetail/{id}/{detailId}")
	public ResponseEntity<?> delCodeDetail(@PathVariable(value="id") String id,
			   							   @PathVariable(value="detailId") String detailId) {						
												
		commandService.deleteAppointmentCodeDetail(id, detailId);
								 						
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}				
	
}
