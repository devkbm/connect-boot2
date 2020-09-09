package com.like.hrm.appointment.web;

import java.util.List;
import java.util.stream.Collectors;

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
import com.like.hrm.appointment.service.AppointmentCodeQueryService;
import com.like.hrm.appointment.service.AppointmentCodeService;

@RestController
public class AppointmentCodeController {

	private AppointmentCodeService appointmentService;	
	
	private AppointmentCodeQueryService appointmentQueryService;

	public AppointmentCodeController(AppointmentCodeService appointmentService
									,AppointmentCodeQueryService appointmentQueryService) {
		this.appointmentService = appointmentService;
		this.appointmentQueryService = appointmentQueryService;
	}
	
	@GetMapping("/hrm/appointmentcode")
	public ResponseEntity<?> getCodeList(AppointmentCodeDTO.SearchCode search) {
																	
		List<AppointmentCode> list = appointmentQueryService.getAppointentCodeList(search);  							
		
		List<AppointmentCodeDTO.SaveCode> dtoList = list.stream()
														.map(r -> AppointmentCodeDTO.SaveCode.convertDTO(r))
														.collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
		
	@GetMapping("/hrm/appointmentcode/{id}")
	public ResponseEntity<?> getCode(@PathVariable(value="id") String id) {
		
		AppointmentCode code = appointmentService.getAppointmentCode(id);
					
		return WebControllerUtil.getResponse(code											
											,String.format("%d 건 조회되었습니다.", code == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/appointmentcode"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody AppointmentCodeDTO.SaveCode code, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveAppointmentCode(code);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/appointmentcode/{id}")
	public ResponseEntity<?> delCode(@PathVariable(value="id") String id) {						
												
		appointmentService.deleteAppintmentCode(id);
								 						
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/appointmentcodedetail")
	public ResponseEntity<?> getCodeDetailList(AppointmentCodeDTO.SearchCodeDetail dto) {
		 				
		List<AppointmentCodeDetail> list = appointmentQueryService.getAppointmentCodeDetailList(dto);

		List<AppointmentCodeDTO.SaveCodeDetail> dtoList = list.stream()
															  .map(r -> AppointmentCodeDTO.SaveCodeDetail.convert(r))
															  .collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/appointmentcodedetail/{id}/{detailId}")
	public ResponseEntity<?> getCodeDetail(@PathVariable(value="id") String id,
										   @PathVariable(value="detailId") String detailId) {
		 		
		AppointmentCodeDetail code = appointmentService.getAppointmentCodeDetail(id, detailId);
					
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
																	
		appointmentService.saveAppointmentCodeDetail(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/appointmentcodedetail/{id}/{detailId}")
	public ResponseEntity<?> delCodeDetail(@PathVariable(value="id") String id,
			   							   @PathVariable(value="detailId") String detailId) {						
												
		appointmentService.deleteAppointmentCodeDetail(id, detailId);
								 						
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}				
	
}
