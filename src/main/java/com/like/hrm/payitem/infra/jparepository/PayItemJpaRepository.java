package com.like.hrm.payitem.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.like.hrm.payitem.boundary.PayItemDTO.SearchPayItem;
import com.like.hrm.payitem.domain.model.PayItem;
import com.like.hrm.payitem.domain.repository.PayItemRepository;
import com.like.hrm.payitem.infra.jparepository.springdata.JpaPayItem;

@Repository
public class PayItemJpaRepository implements PayItemRepository {

	private JpaPayItem jpaPayItem;
	
	public PayItemJpaRepository(JpaPayItem jpaPayItem) {
		this.jpaPayItem = jpaPayItem;		
	}
	
	@Override
	public PayItem getPayItem(String code) {
		return this.jpaPayItem.findById(code).orElse(null);
	}

	@Override
	public void save(PayItem entity) {
		this.jpaPayItem.save(entity);
	}

	@Override
	public void delete(PayItem entity) {
		this.jpaPayItem.delete(entity);
	}

	@Override
	public List<PayItem> getPayItemList(SearchPayItem condition) {		
		return Lists.newArrayList(jpaPayItem.findAll(condition.getBooleanBuilder()));
	}

}
