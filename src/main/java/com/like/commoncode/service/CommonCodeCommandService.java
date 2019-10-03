package com.like.commoncode.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.commoncode.boundary.CodeDTO;
import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.repository.CommonCodeRepository;

@Service
@Transactional
public class CommonCodeCommandService {

	@Resource(name="codeJpaRepository")
	private CommonCodeRepository codeRepository;
				
	public void saveCode(Code code) {		
		codeRepository.saveCode(code);		
	}
	
	public void saveCode(CodeDTO.SaveCode dto) {
		Code parentCode = null; 
		Code code = null;
		
		if (dto.getParentId() != null) {
			parentCode = codeRepository.getCode(dto.getParentId());
		}
		
		code = codeRepository.getCode(dto.getCode());
		
		if (code == null) {
			dto.newCode(parentCode);
		} else {
			dto.modifyCode(code);
		}
		
		
		codeRepository.saveCode(code);		
	}

	public void deleteCode(String commonCodeId) {
		codeRepository.deleteCode(commonCodeId);		
	}
}
