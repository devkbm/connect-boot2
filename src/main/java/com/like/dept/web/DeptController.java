package com.like.dept.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.dept.domain.model.Dept;
import com.like.dept.domain.model.DeptDTOAssembler;
import com.like.dept.domain.repository.DeptRepository;
import com.like.dept.dto.DeptDTO;
import com.like.dept.dto.DeptDTO.DeptHierarchy;
import com.like.dept.dto.DeptDTO.DeptSave;
import com.like.dept.service.DeptService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DeptController {

	@Resource(name="deptJpaRepository")
	private DeptRepository deptRepository;
	
	@Resource(name = "deptService")
	private DeptService deptService;
	
	@GetMapping("/common/depttree")
	public ResponseEntity<?> getDeptHierarchyList(@ModelAttribute DeptDTO.SearchCondition searchCondition) {
							
		List<DeptHierarchy> list = deptService.getDeptHierarchyList();  						 						
		
		return WebControllerUtil.getResponse(list, 
											list.size(), 
											true, 
											String.format("%d 건 조회되었습니다.", list.size()), 
											HttpStatus.OK);
	}
	
	@GetMapping("/common/dept")
	public ResponseEntity<?> getDeptList(@ModelAttribute DeptDTO.SearchCondition searchCondition) {
							
		List<Dept> list = deptService.getDeptList(searchCondition);  						 						
		
		return WebControllerUtil.getResponse(list, 
											list.size(), 
											true, 
											String.format("%d 건 조회되었습니다.", list.size()), 
											HttpStatus.OK);
	}
		
	@GetMapping("/common/dept/{id}")
	public ResponseEntity<?> getDept(@PathVariable String id) {
							
		Dept dept = deptService.getDept(id);  	
		
		DeptSave dto = DeptDTOAssembler.convertDTO(dept);
		
		return WebControllerUtil.getResponse(dto, 
											dto == null ? 0 : 1, 
											true, 
											String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1), 
											HttpStatus.OK);
	}
		
	@RequestMapping(value={"/common/dept"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveDept(@RequestBody DeptDTO.DeptSave dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 					
		
		log.info(dto.toString());
		
		Dept dept = DeptDTOAssembler.toEntity(deptRepository, dto);							
		
		
		
		deptService.saveDept(dept);		
											 				
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}		
	
	@DeleteMapping("/common/dept/{code}")
	public ResponseEntity<?> deleteDept(@PathVariable(value="code") String deptCode) {				
												
		deptService.deleteDept(deptCode);							
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
	
}
