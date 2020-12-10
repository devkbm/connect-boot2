package com.like.hrm.payitem.web;

import java.util.List;
import java.util.stream.Collectors;

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
import com.like.hrm.payitem.boundary.PayTableDTO;
import com.like.hrm.payitem.domain.model.PayTable;
import com.like.hrm.payitem.domain.model.PayTableItem;
import com.like.hrm.payitem.service.PayTableService;

@RestController
public class PayTableController {

	private PayTableService payTableService;
	
	public PayTableController(PayTableService payTableService) {
		this.payTableService = payTableService;
	}
	
	@GetMapping("/hrm/paytable/{id}")
	public ResponseEntity<?> getPayTable(@PathVariable(value="id") Long id) {
		
		PayTable entity = payTableService.getPayTable(id);
						
		PayTableDTO.SavePayTable dto = PayTableDTO.SavePayTable.convert(entity);			
				
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
		
	@PostMapping("/hrm/paytable")
	public ResponseEntity<?> savePayTable(@RequestBody @Valid PayTableDTO.SavePayTable dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
							
		payTableService.save(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
		
	@DeleteMapping("/hrm/paytable/{id}")
	public ResponseEntity<?> deletePayTable(@PathVariable(value="id") Long id) {				
																		
		payTableService.delete(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/paytable/{payTableId}/item")
	public ResponseEntity<?> getPayTableItems(@PathVariable(value="payTableId") Long payTableId) {
		
		List<PayTableItem> entity = payTableService.getPayTableItem(payTableId);
						
		List<PayTableDTO.SavePayTableItem> dto = entity.stream()
													  .map(e -> PayTableDTO.SavePayTableItem.convert(e))
													  .collect(Collectors.toList());			
				
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/paytable/{payTableId}/item/{id}")
	public ResponseEntity<?> getPayTableItem(@PathVariable(value="payTableId") Long payTableId
			                                ,@PathVariable(value="id") Long id) {
		
		PayTableItem entity = payTableService.getPayTableItem(payTableId, id);
						
		PayTableDTO.SavePayTableItem dto = PayTableDTO.SavePayTableItem.convert(entity);			
				
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@PostMapping("/hrm/paytable/item")
	public ResponseEntity<?> savePayTableItem(@RequestBody @Valid PayTableDTO.SavePayTableItem dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
							
		payTableService.save(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
		
	@DeleteMapping("/hrm/paytable/{payTableId}/item/{id}")
	public ResponseEntity<?> deletePayTableItem(@PathVariable(value="payTableId") Long payTableId
            								   ,@PathVariable(value="id") Long id) {				
																		
		payTableService.delete(payTableId, id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
}
