package com.like.hrm.payitem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.payitem.domain.repository.PayItemRepository;

@Transactional
@Service
public class PayItemService {

	private PayItemRepository payItemRepository;
	
	public PayItemService(PayItemRepository payItemRepository) {
		this.payItemRepository = payItemRepository;
	}
	
	
}
