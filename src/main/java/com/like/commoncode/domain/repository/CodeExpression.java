package com.like.commoncode.domain.repository;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.QCode;

public class CodeExpression {

	/**
	 * 사용여부 true, 시작일 <= 현재일 <= 종료일인 코드 검색
	 * @return
	 */
	@QueryDelegate(Code.class)
	public static BooleanExpression enabled(QCode code) {
		
		DateTimeExpression<LocalDateTime> now = DateTimeExpression.currentTimestamp(LocalDateTime.class);
				
		return now.between(code.fromDate, code.toDate);
	}
	
	@QueryDelegate(Code.class)
	public static BooleanExpression isRootNode(QCode code) {							
		return code.parentCode.isNull();
	}
	
	@QueryDelegate(Code.class)
	public static BooleanExpression isLeafNode(QCode code) {							
		return code.parentCode.isNotNull();
	}
}
