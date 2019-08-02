package com.like.commoncode.boundary;

import java.io.Serializable;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class CodeComboDTO implements Serializable {
			
	private static final long serialVersionUID = 1396114277976142701L;

	private String code;
		
	private String codeName;
		
	private String codeNameAbbr;

	@QueryProjection
	public CodeComboDTO(String code, String codeName, String codeNameAbbr) {
		super();
		this.code = code;
		this.codeName = codeName;
		this.codeNameAbbr = codeNameAbbr;
	}		
			
}
