package com.like.hrm.payitem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.payitem.boundary.PayTableDTO;
import com.like.hrm.payitem.domain.model.PayTable;
import com.like.hrm.payitem.domain.model.PayTableItem;
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
	
	public void save(PayTableDTO.SavePayTable dto) {
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
	
	public List<PayTableItem> getPayTableItem(Long payTableId) {
		return this.getPayTable(payTableId).getItems();
	}
	
	public PayTableItem getPayTableItem(Long payTableId, Long id) {
		PayTable entity = this.getPayTable(payTableId);
		
		return entity.get(id);
	}
	
	public void save(PayTableDTO.SavePayTableItem dto) {
		PayTable entity = this.getPayTable(dto.getPayTableId());
		PayTableItem item = entity.get(dto.getId());
		
		if (item == null) {
			entity.add(dto.newEntity(entity));
		} else {
			dto.modifyEntity(item);
		}
		
		payTableRepository.save(entity);
	}
	
	public void delete(Long payTableId, Long id) {
		PayTable entity = this.getPayTable(payTableId);
		
		entity.remove(id);
		
		payTableRepository.save(entity);
	}
}
