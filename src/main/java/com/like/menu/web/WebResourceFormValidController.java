package com.like.menu.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.menu.domain.model.WebResource;
import com.like.menu.service.WebResourceService;

@RestController
public class WebResourceFormValidController {

	private WebResourceService service;
	
	public WebResourceFormValidController(WebResourceService service) {
		this.service = service;
	}
	
	@GetMapping("/common/webresource/{code}/check")
	public ResponseEntity<?> getResource(@PathVariable(value="code") String code) {						
		WebResource resource = service.getResource(code); 							
		Boolean isValid = resource == null ? true : false;
						
		return WebControllerUtil
				.getResponse(isValid
							,isValid == true ? "사용가능한 리소스 코드입니다." : "중복된 리소스 코드가 있습니다."
							,HttpStatus.OK);
	}
	
}
