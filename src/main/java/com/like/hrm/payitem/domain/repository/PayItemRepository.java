package com.like.hrm.payitem.domain.repository;

import java.util.List;

import com.like.hrm.payitem.boundary.PayItemDTO;
import com.like.hrm.payitem.domain.model.PayItem;

public interface PayItemRepository {

	List<PayItem> getPayItemList(PayItemDTO.SearchPayItem condition);
	
	PayItem getPayItem(String code);
	
	void save(PayItem entity);
	
	void delete(PayItem entity);	
}
