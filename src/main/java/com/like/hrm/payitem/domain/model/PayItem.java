package com.like.hrm.payitem.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMPAYITEM")
@EntityListeners(AuditingEntityListener.class)
public class PayItem extends AuditEntity {

	@Id
	@Column(name="CODE")
	String code;
	
	@Column(name="CODE_NM")
	String codeName;
	
	// 지급분, 공제분, 합산분
	@Column(name="ITEM_TYPE")
	String type;
	
	// 급여테이블 사용여부
	@Column(name="PAY_TABLE_YN")
	Boolean usePayTable;	
	
	// 급여테이블
	@Transient
	List<PayTable> payTable = new ArrayList<>();

	@Column(name="SEQ")
	Integer seq;
	
	@Column(name="CMT")
	String comment;
	
	public PayItem(String code
				  ,String codeName
				  ,String type
				  ,Boolean usePayTable
				  ,Integer seq
				  ,String comment) {		
		this.code = code;
		this.codeName = codeName;
		this.type = type;
		this.seq = seq;
		this.comment = comment;
		this.usePayTable = usePayTable;
	}
	
	public void modify(String codeName
					  ,String type
					  ,Boolean usePayTable
					  ,Integer seq
					  ,String comment) {
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
