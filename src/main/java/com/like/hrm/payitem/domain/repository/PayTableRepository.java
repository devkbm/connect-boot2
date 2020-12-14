package com.like.hrm.payitem.domain.repository;

import java.util.List;

import com.like.hrm.payitem.boundary.PayTableDTO;
import com.like.hrm.payitem.domain.model.PayTable;

public interface PayTableRepository {
	
	List<PayTable> getPayTableList(PayTableDTO.SearchPayTable dto);
	
	PayTable getPayTable(Long id);
	
	void save(PayTable entity);
	
	void delete(PayTable entity);
}
