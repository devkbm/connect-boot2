package com.like.hrm.payitem.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayItem {

	String code;
	
	String codeName;
	
	// 지급분, 공제분, 합산분
	String type;
	
	Integer seq;
	
	String comment;
		
	// 급여테이블 사용여부
	Boolean usePayTable;	
	
	// 급여테이블
	List<PayTable> payTable = new ArrayList<>();

	public PayItem(String code
				  ,String codeName
				  ,String type
				  ,Integer seq
				  ,String comment
				  ,Boolean usePayTable) {		
		this.code = code;
		this.codeName = codeName;
		this.type = type;
		this.seq = seq;
		this.comment = comment;
		this.usePayTable = usePayTable;
	}
	
	public void modify(String codeName
					  ,String type
					  ,Integer seq
					  ,String comment
					  ,Boolean usePayTable) {
		this.codeName = codeName;
		this.type = type;
		this.seq = seq;
		this.comment = comment;
		this.usePayTable = usePayTable;
	}
	
	public void add(PayTable entity) {
		this.payTable.add(entity);
	}
	
	public void remove(PayTable entity) {
		this.payTable.remove(entity);
	}
	
}
