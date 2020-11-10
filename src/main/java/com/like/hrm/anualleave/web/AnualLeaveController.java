package com.like.hrm.anualleave.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.anualleave.boundary.AnualLeaveDTO;
import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.service.AnualLeaveService;
import com.like.hrm.appointment.boundary.LedgerDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AnualLeaveController {
	
	private AnualLeaveService anualLeaveService;
	
	public AnualLeaveController(AnualLeaveService anualLeaveService) {
		this.anualLeaveService = anualLeaveService;		
	}

	@GetMapping("/hrm/anualleave/{yyyy}/{empId}")
	public ResponseEntity<?> getAnualLeave(@PathVariable(value="yyyy") Integer yyyy
									  	  ,@PathVariable(value="yyyy") String empId) {
				
		AnualLeave entity = anualLeaveService.getAnualLeave(yyyy, empId);					
		
		AnualLeaveDTO.SaveAnualLeave dto = AnualLeaveDTO.SaveAnualLeave.convertDTO(entity); 
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	@PostMapping("/hrm/anualleave")
	public ResponseEntity<?> saveAnualLeave(@RequestBody AnualLeaveDTO.SaveAnualLeave dto, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		anualLeaveService.saveAnualLeave(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/anualleave/{yyyy}/{empId}")
	public ResponseEntity<?> deleteLedger(@PathVariable(value="yyyy") Integer yyyy
		  	  							 ,@PathVariable(value="yyyy") String empId) {				
																		
		anualLeaveService.deleteAnualLeave(yyyy, empId);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
}
