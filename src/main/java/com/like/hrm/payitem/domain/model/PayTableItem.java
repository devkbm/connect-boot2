package com.like.hrm.payitem.domain.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMPAYTABLEITEM")
@EntityListeners(AuditingEntityListener.class)
public class PayTableItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Column(name="ID")
	String code1;
	
	@Column(name="ID")
	String code2;
	
	@Column(name="ID")
	String code3;
	
	@Column(name="ID")
	BigDecimal ammount;
	
	@Column(name="ID")
	String comment;
		
	PayTable payTable;	
	
	public PayTableItem(PayTable payTable
					   ,String code1
					   ,String code2
					   ,String code3
					   ,BigDecimal ammount
					   ,String comment) {
		this.payTable = payTable;
		this.code1 = code1;
		this.code2 = code2;
		this.code3 = code3;
		this.ammount = ammount;
		this.comment = comment;
	}
	
	public void modify(BigDecimal ammount
			          ,String comment) {
		this.ammount = ammount;
		this.comment = comment;
	}
}
