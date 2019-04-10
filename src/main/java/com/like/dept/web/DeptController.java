package com.like.dept.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.dept.domain.model.Dept;
import com.like.dept.domain.model.DeptDTOAssembler;
import com.like.dept.dto.DeptDTO;
import com.like.dept.service.DeptService;

@RestController
public class DeptController {

	@Resource(name = "deptService")
	private DeptService deptService;
		
	@RequestMapping(value={"/common/dept"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getDeptList(@ModelAttribute DeptDTO.SearchCondition searchCondition) {
							
		List<Dept> list = deptService.getDeptList(searchCondition);  						 						
		
		return WebControllerUtil.getResponse(list, 
											list.size(), 
											true, 
											String.format("%d 건 조회되었습니다.", list.size()), 
											HttpStatus.OK);
	}
		
	@PostMapping(value={"/common/dept"})
	public ResponseEntity<?> saveCode(@RequestBody DeptDTO.DeptSave dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 					
		
		Dept dept2 = deptService.getDept(dto.getDeptCode());
		
		if (dept2 != null)
			throw new IllegalArgumentException("부서가 이미 존재합니다.");
		
		Dept parentDept = dto.getParentDeptCode() == null ? null : deptService.getDept(dto.getParentDeptCode());
		
		Dept dept = DeptDTOAssembler.createEntity(dto, parentDept);							
				
		deptService.saveDept(dept);		
											 				
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}
		
	@PutMapping(value={"/common/dept"})
	public ResponseEntity<?> updateDeptCode(@RequestBody DeptDTO.DeptSave dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 					
				
		Dept dept = deptService.getDept(dto.getDeptCode());
		
		Dept parentDept = dto.getParentDeptCode() == null ? null : deptService.getDept(dto.getParentDeptCode()); 
				
		DeptDTOAssembler.mergeEntity(dept, dto, parentDept);			
											
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
