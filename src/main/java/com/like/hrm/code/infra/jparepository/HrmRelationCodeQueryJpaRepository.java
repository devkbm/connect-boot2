package com.like.hrm.code.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.code.boundary.HrmRelationCodeDTO.SearchHrmRelationCode;
import com.like.commoncode.domain.model.QCode;
import com.like.hrm.code.boundary.QSaveHrmRelationCode;
import com.like.hrm.code.boundary.SaveHrmRelationCode;
import com.like.hrm.code.domain.model.QHrmRelationCode;
import com.like.hrm.code.domain.model.QHrmType;
import com.like.hrm.code.domain.model.QHrmTypeDetailCode;
import com.like.hrm.code.domain.repository.HrmRelationCodeQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class HrmRelationCodeQueryJpaRepository implements HrmRelationCodeQueryRepository {

	private JPAQueryFactory	queryFactory;
	private static final QCode qCode = QCode.code1;
	private static final QHrmType qHrmType = QHrmType.hrmType1;
	private static final QHrmTypeDetailCode qHrmTypeDetailCode = QHrmTypeDetailCode.hrmTypeDetailCode;
	private static final QHrmRelationCode qHrmRelationCode = QHrmRelationCode.hrmRelationCode;
	
	
	public HrmRelationCodeQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
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
	
	
}
