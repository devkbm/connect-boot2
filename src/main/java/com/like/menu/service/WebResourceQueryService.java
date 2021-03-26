package com.like.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.menu.boundary.WebResourceDTO;
import com.like.menu.domain.model.WebResource;
import com.like.menu.domain.repository.WebResourceQueryRepository;

@Service
@Transactional(readOnly=true)
public class WebResourceQueryService {

	private WebResourceQueryRepository repository;
	
	public WebResourceQueryService(WebResourceQueryRepository repository) {
		this.repository = repository;		
	}
	
	public List<WebResource> getResourceList(WebResourceDTO.SearchWebResource condition) {
		return repository.getResourceList(condition);
	}
}
