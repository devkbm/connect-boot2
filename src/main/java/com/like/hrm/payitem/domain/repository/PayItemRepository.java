package com.like.hrm.payitem.domain.repository;

import com.like.hrm.payitem.domain.model.PayItem;

public interface PayItemRepository {

	PayItem getPayItem(String code);
	
	void save(PayItem entity);
	
	void delete(PayItem entity);
}
