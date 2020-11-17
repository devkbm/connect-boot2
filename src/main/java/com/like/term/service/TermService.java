package com.like.term.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.term.boundary.TermDTO;
import com.like.term.domain.model.TermDictionary;
import com.like.term.domain.repository.TermRepository;

@Service("termService")
@Transactional
public class TermService {
	
    @Resource(name="termJpaRepository")
	private TermRepository termRepository;      
	
	public TermDictionary getTerm(Long pkTerm) {
		return termRepository.getTerm(pkTerm);
	}
	
	public List<TermDictionary> getTermList() {
		return termRepository.getTermList();
	}
	
	public List<TermDictionary> getTermList(TermDTO.QueryCondition condition) {
		return termRepository.getTermList(condition);
	}

	public void saveTerm(TermDictionary term) {
		termRepository.saveTerm(term);
	}
	
	public void saveTerm(TermDTO.SaveTerm dto) {
		TermDictionary entity = null;
		
		if (dto.getPkTerm() == null) {
			entity = dto.newEntity();
		} else {
			entity = termRepository.getTerm(dto.getPkTerm());
			dto.modifyEntity(entity);
		}
		
		termRepository.saveTerm(entity);
	}	
	
	public void deleteTerm(Long pkTerm) {
		termRepository.deleteTerm(pkTerm);		
	}	
		
}
