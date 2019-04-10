package com.like.term.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.term.domain.model.TermDictionary;
import com.like.term.domain.repository.TermRepository;
import com.like.term.dto.TermDTO;

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
	
	public void saveTerm(List<TermDictionary> termList) {
		termRepository.saveTerm(termList);		
	}
	
	public void deleteTerm(Long pkTerm) {
		termRepository.deleteTerm(pkTerm);		
	}
	
	public void deleteTerm(List<TermDictionary> termList) {
		termRepository.deleteTerm(termList);	
	}
		
}