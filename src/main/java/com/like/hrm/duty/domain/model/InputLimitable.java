package com.like.hrm.duty.domain.model;

/**
 * 근태 입력 제한 인퍼테이스
 * 근태입력 제한 룰이 추가될 경우 해당 인터페이스를 상속하여 구현한다.
 */
public interface InputLimitable {
	
	boolean isLimit(DutyApplication entity);
	
}
