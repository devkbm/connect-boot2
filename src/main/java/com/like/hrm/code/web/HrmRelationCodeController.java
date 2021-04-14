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
import com.like.hrm.code.boundary.SaveHrmRelationCode;
import com.like.hrm.code.domain.model.HrmRelationCode;
import com.like.hrm.code.service.HrmRelationCodeService;

@RestController
public class HrmRelationCodeController {

	private HrmRelationCodeService service;
	
	public HrmRelationCodeController(HrmRelationCodeService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/hrmrelation/{id}")
	public ResponseEntity<?> getHrmRelationCode(@PathVariable(value="id") Long id) {				
		
		HrmRelationCode hrmType = service.getRelationCode(id);
					
		return WebControllerUtil.getResponse(hrmType											
											,String.format("%d 건 조회되었습니다.", hrmType == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/hrmrelation"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveHrmRelationCode(@RequestBody SaveHrmRelationCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		service.saveRelationCode(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/hrmrelation/{id}")
	public ResponseEntity<?> deleteHrmRelationCode(@PathVariable(value="id") Long id) {				
						
		service.deleteRelationCode(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
}
