package com.like.hrm.code.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.code.boundary.SaveHrmRelationCode;
import com.like.hrm.code.domain.model.HrmRelationCode;
import com.like.hrm.code.domain.repository.HrmRelationCodeRepository;

@Service
@Transactional
public class HrmRelationCodeService {

	private HrmRelationCodeRepository repository;
	
	public HrmRelationCodeService(HrmRelationCodeRepository repository) {
		this.repository = repository;
	}
	
	public HrmRelationCode getRelationCode(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void saveRelationCode(SaveHrmRelationCode dto) {
		HrmRelationCode entity = null;
		
		if (dto.getRelationId() != null) {
			entity = this.getRelationCode(dto.getRelationId());
		} else {
			entity = dto.newHrmRelationCode();
		}
		
		repository.save(entity);
	}
	
	public void deleteRelationCode(Long id) {
		repository.deleteById(id);
	}
}
