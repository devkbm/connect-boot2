package com.like.hrm.util;

import java.math.BigDecimal;
import java.util.List;

//
//Calc#calc 메서드의 수행 결과를 담고있는 클래스.
//
class CalcResult {
	public final List<Token<Type>> postfix;
	public final BigDecimal result;

	public CalcResult(List<Token<Type>> postfix, BigDecimal result) {
		this.postfix = postfix;
		this.result = result;
	}
}