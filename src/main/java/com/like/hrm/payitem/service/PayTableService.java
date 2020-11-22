package com.like.hrm.payitem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.payitem.boundary.PayItemDTO;
import com.like.hrm.payitem.domain.model.PayTable;
import com.like.hrm.payitem.domain.repository.PayTableRepository;

@Transactional
@Service
public class PayTableService {

	private PayTableRepository payTableRepository;
	
	public PayTableService(PayTableRepository payTableRepository) {
		this.payTableRepository = payTableRepository;
	}
	
	public PayTable getPayTable(Long id) {
		return this.payTableRepository.getPayTable(id);
	}
	
	public void save(PayItemDTO.SavePayTable dto) {
		PayTable entity = dto.getId() != null ? payTableRepository.getPayTable(dto.getId()) : null;
		
		if (entity == null) {
			entity = dto.newEntity();
		} else {
			dto.modifyEntity(entity);
		}
		
		payTableRepository.save(entity);
	}
	
	public void delete(Long id) {
		PayTable entity = this.getPayTable(id);
		
		this.payTableRepository.delete(entity);
	}
}
