package com.like.term.domain.repository;

import java.util.List;

import com.like.term.domain.model.TermDictionary;
import com.like.term.dto.TermDTO;

public interface TermRepository {
	
	TermDictionary getTerm(Long pkTerm);	
	
	List<TermDictionary> getTermList();
	
	List<TermDictionary> getTermList(TermDTO.QueryCondition condition);
	
	void saveTerm(TermDictionary term);
	
	void saveTerm(List<TermDictionary> termList);
	
	void deleteTerm(Long pkTerm);
	
	void deleteTerm(List<TermDictionary> termList);
}
