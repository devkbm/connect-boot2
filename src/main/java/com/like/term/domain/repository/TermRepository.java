package com.like.term.domain.repository;

import java.util.List;

import com.like.term.boundary.TermDTO;
import com.like.term.domain.model.TermDictionary;

public interface TermRepository {
	
	TermDictionary getTerm(Long pkTerm);	
	
	List<TermDictionary> getTermList();
	
	List<TermDictionary> getTermList(TermDTO.QueryCondition condition);
	
	void saveTerm(TermDictionary term);	
	
	void deleteTerm(Long pkTerm);
	
}
