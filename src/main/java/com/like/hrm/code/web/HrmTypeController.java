package com.like.hrm.code.web;

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
import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.code.service.HrmTypeService;

@RestController
public class HrmTypeController {

	private HrmTypeService service;			

	public HrmTypeController(HrmTypeService service) {
		this.service = service;		
	}							
	
	@GetMapping("/hrm/hrmtype/{id}")
	public ResponseEntity<?> getHrmType(@PathVariable(value="id") String id) {
		
		HrmTypeDTO.SaveCode hrmType = service.getHrmTypeDTO(id);
					
		return WebControllerUtil.getResponse(hrmType											
											,String.format("%d 건 조회되었습니다.", hrmType == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/hrmtype"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveHrmType(@RequestBody HrmTypeDTO.SaveCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		service.saveHrmType(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
		
	@DeleteMapping("/hrm/hrmtype/{id}")
	public ResponseEntity<?> deleteHrmType(@PathVariable(value="id") String id) {				
																		
		service.deleteHrmType(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}			
	
	
	@GetMapping("/hrm/typedetailcode/{id}")
	public ResponseEntity<?> getTypeDetailCode(@PathVariable(value="id") String id) {
		
		HrmTypeDetailCodeDTO.SaveCode dto = service.getTypeDetailCodeDTO(id);
					
		return WebControllerUtil.getResponse(dto
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/typedetailcode"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveTypeDetailCode(@RequestBody HrmTypeDetailCodeDTO.SaveCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		service.saveTypeDetailCode(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/typedetailcode/{id}")
	public ResponseEntity<?> deleteTypeDetailCode(@PathVariable(value="id") String id) {				
																		
		service.deleteTypeDetailCode(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	

	
	
}
