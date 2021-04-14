package com.like.menu.web;

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

import com.like.core.web.exception.ControllerException;
import com.like.core.web.util.WebControllerUtil;
import com.like.menu.boundary.WebResourceDTO;
import com.like.menu.boundary.WebResourceDTO.SaveWebResource;
import com.like.menu.domain.model.WebResource;
import com.like.menu.service.WebResourceService;

@RestController
public class WebResourceController {

	private WebResourceService service;
	
	public WebResourceController(WebResourceService menuCommandService) {
		this.service = menuCommandService;		
	}
	
	@GetMapping("/common/webresource/{code}")
	public ResponseEntity<?> getResource(@PathVariable(value="code") String code) {				
		
		WebResource resource = service.getResource(code); 							
		
		WebResourceDTO.SaveWebResource dto = SaveWebResource.convertDTO(resource);
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0)
											,HttpStatus.OK);
	}
		
	@PostMapping("/common/webresource")
	public ResponseEntity<?> saveResource(@RequestBody @Valid WebResourceDTO.SaveWebResource dto, BindingResult result) throws Exception {
										
		if ( result.hasErrors()) {
			throw new ControllerException(result.getAllErrors().toString());
		} 
																	
		service.saveWebResource(dto);																						
										 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/common/webresource/{code}")
	public ResponseEntity<?> delResource(@PathVariable(value="code") String code) {				
												
		service.deleteWebResource(code);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
}
