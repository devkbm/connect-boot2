package com.like.hrm.payitem.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.payitem.boundary.PayItemDTO;
import com.like.hrm.payitem.domain.model.PayItem;
import com.like.hrm.payitem.service.PayItemService;

@RestController
public class PayItemController {

	private PayItemService payItemService;
	
	public PayItemController(PayItemService payItemService) {
		this.payItemService = payItemService;		
	}
	
	@GetMapping("/hrm/payitem/{code}")
	public ResponseEntity<?> getPayItem(@PathVariable(value="code") String code) {
		
		PayItem entity = payItemService.getPayItem(code);
						
		PayItemDTO.SavePayItem dto = PayItemDTO.SavePayItem.convert(entity);			
				
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
		
	@PostMapping("/hrm/payitem")
	public ResponseEntity<?> saveDutyCode(@RequestBody @Valid PayItemDTO.SavePayItem dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
							
		payItemService.save(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
		
	@DeleteMapping("/hrm/payitem/{code}")
	public ResponseEntity<?> deleteDutyCode(@PathVariable(value="code") String code) {				
																		
		payItemService.delete(code);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
}
