package com.like.hrm.payitem.domain.repository;

import com.like.hrm.payitem.domain.model.PayTable;

public interface PayTableRepository {
	
	PayTable getPayTable(Long id);
	
	void save(PayTable entity);
	
	void delete(PayTable entity);
}
