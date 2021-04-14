package com.like.menu.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.menu.boundary.WebResourceDTO;
import com.like.menu.boundary.WebResourceDTO.SaveWebResource;
import com.like.menu.domain.model.WebResource;
import com.like.menu.service.WebResourceQueryService;

@RestController
public class WebResourceQueryController {

	private WebResourceQueryService service;
	
	public WebResourceQueryController(WebResourceQueryService service) {
		this.service = service; 
	}
	
	@GetMapping("/common/webresource")
	public ResponseEntity<?> getWebResourceList(WebResourceDTO.SearchWebResource condition) {							 			
		
		List<WebResource> list = service.getResourceList(condition);
										
		List<WebResourceDTO.SaveWebResource> dtoList = list.stream()
														   .map(e -> SaveWebResource.convertDTO(e))
														   .collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK); 
	}
}
