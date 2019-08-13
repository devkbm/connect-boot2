package com.like.common.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class SessionUtil {

	/**
	 * 현재 로그인한 세션의 유저 아이디를 가져온다.
	 * @return String 유저아이디
	 */
	public static String getUserId() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
