package com.like.hrm.code.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.HrmTypeDetailCode;
import com.like.hrm.code.domain.model.QHrmType;
import com.like.hrm.code.domain.model.QHrmTypeDetailCode;
import com.like.hrm.code.domain.repository.HrmCodeRepository;
import com.like.hrm.code.infra.jparepository.springdata.JpaHrmType;
import com.like.hrm.code.infra.jparepository.springdata.JpaTypeDetailCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class HrmCodeJpaRepository implements HrmCodeRepository {

	@Autowired
	private JPAQueryFactory	queryFactory;	
			
	@Autowired
	private JpaHrmType japHrmType;		
	
	@Autowired
	private JpaTypeDetailCode jpaTypeDetailCode;				

	@Override
	public List<HrmType> getHrmTypeList(HrmTypeDTO.SearchHrmType condition) {
		return queryFactory
				.selectFrom(QHrmType.hrmType1)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public HrmType getHrmType(String id) {
		Optional<HrmType> entity = japHrmType.findById(id);
		return entity.orElse(null);
	}

	@Override
	public void saveHrmType(HrmType hrmType) {
		japHrmType.save(hrmType);
		
	}

	@Override
	public void deleteHrmType(HrmType hrmType) {
		japHrmType.delete(hrmType);		
	}
	
	@Override
	public HrmTypeDetailCode getTypeDetailCode(String id) {		 
		Optional<HrmTypeDetailCode> entity = jpaTypeDetailCode.findById(id);
		return entity.orElse(null);
	}

	@Override
	public List<HrmTypeDetailCode> getTypeDetailCodeList(HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode condition) {		
		return queryFactory
				.selectFrom(QHrmTypeDetailCode.hrmTypeDetailCode)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public void saveTypeDetailCode(HrmTypeDetailCode typeDetailCode) {
		jpaTypeDetailCode.save(typeDetailCode);		
	}

	@Override
	public void deleteTypeDetailCode(HrmTypeDetailCode typeDetailCode) {				
		jpaTypeDetailCode.delete(typeDetailCode);		
	}
	
}
