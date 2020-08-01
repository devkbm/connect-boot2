package com.like.hrm.code.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.hrm.code.boundary.HrmRelationCodeDTO.SearchHrmRelationCode;
import com.like.commoncode.domain.model.QCode;
import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.code.boundary.QSaveHrmRelationCode;
import com.like.hrm.code.boundary.SaveHrmRelationCode;
import com.like.hrm.code.domain.model.HrmRelationCode;
import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.HrmTypeDetailCode;
import com.like.hrm.code.domain.model.QHrmRelationCode;
import com.like.hrm.code.domain.model.QHrmType;
import com.like.hrm.code.domain.model.QHrmTypeDetailCode;
import com.like.hrm.code.domain.repository.HrmCodeRepository;
import com.like.hrm.code.infra.jparepository.springdata.JpaHrmRelationCode;
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

	@Autowired
	private JpaHrmRelationCode jpaHrmRelationCode; 
	
	private static final QCode qCode = QCode.code1;
	private static final QHrmType qHrmType = QHrmType.hrmType1;
	private static final QHrmTypeDetailCode qHrmTypeDetailCode = QHrmTypeDetailCode.hrmTypeDetailCode;
	private static final QHrmRelationCode qHrmRelationCode = QHrmRelationCode.hrmRelationCode;
	
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

	@Override
	public HrmRelationCode getRelationCode(Long id) {
		Optional<HrmRelationCode> entity = jpaHrmRelationCode.findById(id);
		return entity.orElse(null);
	}

	@Override
	public void saveRelationCode(HrmRelationCode entity) {
		jpaHrmRelationCode.save(entity);
	}

	@Override
	public void deleteRelationCode(HrmRelationCode entity) {		
		jpaHrmRelationCode.delete(entity);
	}

	@Override
	public List<SaveHrmRelationCode> getRelationCodeList(SearchHrmRelationCode condition) {
		
		QHrmType qHrmType2 = QHrmType.hrmType1;
		QHrmTypeDetailCode qHrmTypeDetailCode2 = QHrmTypeDetailCode.hrmTypeDetailCode;
		
		return queryFactory
				.select(new QSaveHrmRelationCode(
						qHrmRelationCode.relationId
					   ,qHrmRelationCode.relCode
					   ,qCode.codeName
					   ,qHrmRelationCode.parentTypeId
					   ,qHrmType.codeName
					   ,qHrmRelationCode.parentDetailId
					   ,qHrmTypeDetailCode.codeName
					   ,qHrmRelationCode.childTypeId
					   ,qHrmType2.codeName
					   ,qHrmRelationCode.childDetailId
					   ,qHrmTypeDetailCode2.codeName
						))
				.from(qHrmRelationCode)
				.join(qCode)
					.on(qHrmRelationCode.relCode.eq(qCode.id))
				.join(qHrmType)
					.on(qHrmRelationCode.parentTypeId.eq(qHrmType.id))
				.join(qHrmTypeDetailCode)
					.on(qHrmRelationCode.parentDetailId.eq(qHrmTypeDetailCode.id))
				.join(qHrmType2)
					.on(qHrmRelationCode.childTypeId.eq(qHrmType2.id))
				.join(qHrmTypeDetailCode2)
					.on(qHrmRelationCode.childDetailId.eq(qHrmTypeDetailCode2.id))
				.where(condition.getBooleanBuilder())
				.fetch();			
	}

	@Override
	public boolean isHrmType(String id) { 
		return japHrmType.existsById(id);
	}

	@Override
	public boolean isTypeDetailCode(String id) {
		return jpaTypeDetailCode.existsById(id);
	}

	@Override
	public boolean isRelationCode(Long id) {
		return jpaHrmRelationCode.existsById(id);
	}
	
}
