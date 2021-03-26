package com.like.menu.domain.repository;

import java.util.List;

import com.like.menu.boundary.WebResourceDTO;
import com.like.menu.domain.model.WebResource;

public interface WebResourceQueryRepository {

	List<WebResource> getResourceList(WebResourceDTO.SearchWebResource condition);
}
