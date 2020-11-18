package com.like.term.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.like.term.boundary.TermDTO;
import com.like.term.domain.model.QTermDictionary;
import com.like.term.domain.model.TermDictionary;
import com.like.term.domain.repository.TermRepository;
import com.like.term.infra.jparepository.springdata.JpaTerm;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TermJpaRepository implements TermRepository {
	
	private final QTermDictionary qTermDictionary = QTermDictionary.termDictionary;
		
	private JPAQueryFactory  queryFactory;
		
	private JpaTerm jpaTerm;

	public TermJpaRepository(JPAQueryFactory queryFactory
							,JpaTerm jpaTerm) {
		this.queryFactory = queryFactory;
		this.jpaTerm = jpaTerm;
	}	
	
	@Override
	public TermDictionary getTerm(Long pkTerm) {
		Optional<TermDictionary> entity = jpaTerm.findById(pkTerm); 
		
		return entity.orElse(null);
	}
	
	@Override
	public List<TermDictionary> getTermList() {
		return jpaTerm.findAll();
	}
	
	@Override
	public List<TermDictionary> getTermList(TermDTO.SearchTerm condition) {									
		return queryFactory.selectFrom(qTermDictionary)
						   .where(condition.getBooleanBuilder())
						   .fetch();
	}

	@Override
	public void saveTerm(TermDictionary term) {
		jpaTerm.save(term);			
	}

	@Override
	public void deleteTerm(Long pkTerm) {
		jpaTerm.deleteById(pkTerm);		
	}
				
}
