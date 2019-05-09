package com.like.common.vo;

/**
 * 총길이 320 64(주소) + 1(@) + 255(도메인)
 */
public class Email {

	/**
	 * RFC-2822에 정의 64
	 */
	String userId;
	
	/**
	 * RFC-2822에 정의 255
	 */
	String domainName;
	
	public String getFullAddress() {
		return this.userId + "@" + this.domainName;
	}
}
