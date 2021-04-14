package com.like.core.vo;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * RFC-2822 총길이 320 64(로컬파트) + 1(@) + 255(도메인)
 * 이메일주소 검증 참조 사이트 https://emailregex.com/
 * 패턴매칭 참고 사이트 https://coding-factory.tistory.com/529
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Email {
	
	@Column(name="EMAIL")
	private String address;	
	
	public Email(String address) {		
		if (!Email.isValid(address))
			throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다. "+ address);
		
		this.address = address;
	}
	
	public String getFullAddress() {
		return this.address;
	}
	
	public String getLocalPart() {
		return this.address.split("@")[0];
	}
	
	public String getDomain() {
		return this.address.split("@")[1];
	}
	
	public boolean isValid() {
		return Email.isValid(this.address);
	}
	
	public static boolean isValid(String email) { 
		boolean err = false; 
		String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"; 
				
		if(Pattern.compile(regex).matcher(email).matches()) { 
			err = true; 
		} 
		
		return err; 
	}
		
	public static void main(String[] args) {
		String mail = "kbm0417@nate.com";
		
		Email email = new Email(mail);
		
		System.out.println(Email.isValid(mail));
		System.out.println(email.getLocalPart());
		System.out.println(email.getDomain());
	}
}
