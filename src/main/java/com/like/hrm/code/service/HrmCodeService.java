package com.like.hrm.code.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.code.domain.model.HrmRelationCode;
import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.HrmTypeDetailCode;
import com.like.hrm.code.domain.repository.HrmCodeRepository;

@Service
@Transactional
public class HrmCodeService {

	private HrmCodeRepository hrmCodeRepository;
		
	public HrmCodeService(HrmCodeRepository hrmCodeRepository) {		
		this.hrmCodeRepository = hrmCodeRepository;
	}
				
	/*public List<HrmType> getHrmDeptTypeList() {
		return appointmentCodeRepository.getDeptTypeList();
	}*/
	
	public HrmType getHrmType(String id) {
		return hrmCodeRepository.getHrmType(id);
	}
	
	public HrmTypeDTO.SaveCode getHrmTypeDTO(String code) {
		HrmType entity = this.getHrmType(code);
		
		return HrmTypeDTO.SaveCode.convert(entity);
	}
	
	public void saveHrmType(HrmTypeDTO.SaveCode dto) {
		HrmType hrmType = null;
		
		if (dto.getId() != null) {
			hrmType = hrmCodeRepository.getHrmType(dto.getId());
		}
		
		if (hrmType == null) {
			hrmType = dto.newHrmType();
		} else {					
			hrmType = dto.changeInfo(hrmType);
		}
		
		hrmCodeRepository.saveHrmType(hrmType);		
	}	

	public void deleteHrmType(String id) {
		HrmType hrmType = hrmCodeRepository.getHrmType(id);
		
		if (hrmType == null) {
			throw new EntityNotFoundException(id + " 엔티티가 존재하지 않습니다.");
		}
		
		hrmCodeRepository.deleteHrmType(hrmType);					
	}
	
	public HrmTypeDetailCode getTypeDetailCode(String id) {
		return hrmCodeRepository.getTypeDetailCode(id);
	}
	
	public HrmTypeDetailCodeDTO.SaveCode getTypeDetailCodeDTO(String id) {
		HrmTypeDetailCode entity = hrmCodeRepository.getTypeDetailCode(id);				
		
		return HrmTypeDetailCodeDTO.SaveCode.convert(entity);
	}
	
	public void saveTypeDetailCode(HrmTypeDetailCodeDTO.SaveCode dto) {		
		HrmTypeDetailCode typeDetailCode = null;
		
		if (dto.getId() != null) {
			typeDetailCode = hrmCodeRepository.getTypeDetailCode(dto.getId());
		}
		
		if (typeDetailCode == null) {
			typeDetailCode = dto.newTypeDetailCode();
		} else {
			typeDetailCode = dto.changeInfo(typeDetailCode);
		}
		
		hrmCodeRepository.saveTypeDetailCode(typeDetailCode);
	}
	
	public void deleteTypeDetailCode(String id) {
		HrmTypeDetailCode typeDetailCode = hrmCodeRepository.getTypeDetailCode(id);
		
		if (typeDetailCode == null) {
			throw new EntityNotFoundException(id + " 엔티티가 존재하지 않습니다.");
		}
		
		hrmCodeRepository.deleteTypeDetailCode(typeDetailCode);
	}
	
	public HrmRelationCode getRelationCode(Long id) {
		return hrmCodeRepository.getRelationCode(id);
	}
	
	public void saveRelationCode(HrmRelationCode entity) {
		hrmCodeRepository.saveRelationCode(entity);
	}
	
	public void deleteRelationCode(Long id) {
		HrmRelationCode entity = hrmCodeRepository.getRelationCode(id);
		hrmCodeRepository.deleteRelationCode(entity);
	}
	
}
