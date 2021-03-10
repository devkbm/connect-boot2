package com.like.common.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Address {

	@Column(name="POST_NO")
	private String post_number;
	
	@Column(name="MAIN_ADDR")
	private String main_address;
	
	@Column(name="SUB_ADDR")
	private String sub_address;
	
	public Address(String post_number
				  ,String main_address
				  ,String sub_address) {
		this.post_number = post_number;
		this.main_address = main_address;
		this.sub_address = sub_address;
	}
			
	public String getFullAddress() {
		return this.main_address + " " + this.sub_address; 
	}
}
