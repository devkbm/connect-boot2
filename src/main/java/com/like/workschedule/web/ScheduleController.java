package com.like.workschedule.web;

import javax.validation.Valid;

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

import com.like.core.web.exception.ControllerException;
import com.like.core.web.util.WebControllerUtil;
import com.like.workschedule.boundary.ScheduleDTO;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.service.ScheduleService;

@RestController
public class ScheduleController {

	private ScheduleService service;
	
	public ScheduleController(ScheduleService service) {
		this.service = service;
	}
	
	@GetMapping(value={"/grw/schedule/{id}"})
	public ResponseEntity<?> getSchedule(@PathVariable(value="id") Long id) {
						
		Schedule entity = service.getSchedule(id);							
		
		ScheduleDTO.ScheduleResponse dto = ScheduleDTO.convertResDTO(entity);
		
		return WebControllerUtil
				.getResponse(dto
							,dto == null ? 0 : 1
							,dto == null ? false : true
							,"조회 되었습니다."
							,HttpStatus.OK);													
	}
	
	@RequestMapping(value={"/grw/schedule"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody ScheduleDTO.SaveSchedule dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
					
		service.saveSchedule(dto);		
										 					
		return WebControllerUtil
				.getResponse(dto
							,dto != null ? 1 : 0
							,true
							,String.format("%d 건 저장되었습니다.", dto != null ? 1 : 0)
							,HttpStatus.OK);
	}
	
	@DeleteMapping(value={"/grw/schedule/{id}"})
	public ResponseEntity<?> deleteSchedule(@PathVariable(value="id") Long id) {
						
		service.deleteSchedule(id);							
				
		return WebControllerUtil
				.getResponse(null
							,1
							,true
							,"삭제 되었습니다."
							,HttpStatus.OK);													
	}
}
