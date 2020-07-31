package com.like.hrm.code.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.appointment.boundary.ChangeableTypeDTO;
import com.like.hrm.appointment.boundary.ChangeableTypeDTO.EnumDTO;
import com.like.hrm.code.boundary.HrmRelationCodeDTO;
import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.code.boundary.SaveHrmRelationCode;
import com.like.hrm.code.domain.model.HrmRelationCode;
import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.HrmTypeDetailCode;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;
import com.like.hrm.code.service.HrmCodeQueryService;
import com.like.hrm.code.service.HrmCodeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HrmCodeController {

	private HrmCodeService hrmCodeService;	
	
	private HrmCodeQueryService hrmCodeQueryService;

	public HrmCodeController(HrmCodeService hrmCodeService
							,HrmCodeQueryService hrmCodeQueryService) {
		this.hrmCodeService = hrmCodeService;
		this.hrmCodeQueryService = hrmCodeQueryService;
	}
			
	@GetMapping("/hrm/typelist")
	public ResponseEntity<?> getTypeList() {
		
		List<ChangeableTypeDTO.EnumDTO> list = new ArrayList<ChangeableTypeDTO.EnumDTO>();
		
		for (HrmTypeEnum menuType : HrmTypeEnum.values()) {			
			list.add(new EnumDTO(menuType.getCode(), menuType.getName()));
		}										
					
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	/*
	@GetMapping("/hrm/codelist/{code}")
	public ResponseEntity<?> getTypeCodeList(@PathVariable(value="code") String code) {
		
		List<ChangeableCodeDTO.EnumDTO> list = null;
		
		list = hrmCodeService.getChangeableCodeDTO(HrmTypeEnum.valueOf(code));		
					
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	*/
	
	@GetMapping("/hrm/hrmtype")
	public ResponseEntity<?> getHrmTypeList(HrmTypeDTO.SearchHrmType dto) {
		
		List<HrmType> list = hrmCodeQueryService.getHrmDeptTypeList(dto);												
					
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/hrmtype/{id}")
	public ResponseEntity<?> getHrmType(@PathVariable(value="id") String id) {
		
		HrmTypeDTO.SaveCode hrmType = hrmCodeService.getHrmTypeDTO(id);
					
		return WebControllerUtil.getResponse(hrmType											
											,String.format("%d 건 조회되었습니다.", hrmType == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/hrmtype"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveHrmType(@RequestBody HrmTypeDTO.SaveCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		hrmCodeService.saveHrmType(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
		
	@DeleteMapping("/hrm/hrmtype/{id}")
	public ResponseEntity<?> deleteHrmType(@PathVariable(value="id") String id) {				
																		
		hrmCodeService.deleteHrmType(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	
	@GetMapping("/hrm/typedetailcode")
	public ResponseEntity<?> getHrmTypeDetailCodeList(HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode dto) {
		
		List<HrmTypeDetailCode> list = hrmCodeQueryService.getHrmDeptTypeList(dto);												
					
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	
	@GetMapping("/hrm/typedetailcode/{id}")
	public ResponseEntity<?> getTypeDetailCode(@PathVariable(value="id") String id) {
		
		HrmTypeDetailCodeDTO.SaveCode dto = hrmCodeService.getTypeDetailCodeDTO(id);
					
		return WebControllerUtil.getResponse(dto
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/typedetailcode"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveTypeDetailCode(@RequestBody HrmTypeDetailCodeDTO.SaveCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		hrmCodeService.saveTypeDetailCode(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/typedetailcode/{id}")
	public ResponseEntity<?> deleteTypeDetailCode(@PathVariable(value="id") String id) {				
																		
		hrmCodeService.deleteTypeDetailCode(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/hrmrelation")
	public ResponseEntity<?> getHrmRelationCode(HrmRelationCodeDTO.SearchHrmRelationCode dto) {
						
		List<?> list = hrmCodeQueryService.getHrmRelationCodeList(dto);
					
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/hrmrelation/{id}")
	public ResponseEntity<?> getHrmRelationCode(@PathVariable(value="id") Long id) {
		
		log.info(id.toString());
		
		HrmRelationCode hrmType = hrmCodeService.getRelationCode(id);
					
		return WebControllerUtil.getResponse(hrmType											
											,String.format("%d 건 조회되었습니다.", hrmType == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/hrmrelation"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveHrmRelationCode(@RequestBody SaveHrmRelationCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		hrmCodeService.saveRelationCode(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/hrmrelation/{id}")
	public ResponseEntity<?> deleteHrmRelationCode(@PathVariable(value="id") Long id) {				
						
		hrmCodeService.deleteRelationCode(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
}
