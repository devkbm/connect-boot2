package com.like.hrm.appointment.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.ChangeableTypeDTO;
import com.like.hrm.appointment.boundary.ChangeableTypeDTO.EnumDTO;
import com.like.hrm.appointment.boundary.DeptTypeDTO;
import com.like.hrm.appointment.boundary.JobTypeDTO;
import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.enums.ChangeType;
import com.like.hrm.appointment.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AppointmentController {

	private AppointmentService appointmentService;	

	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@GetMapping("/hrm/appointmentcode")
	public ResponseEntity<?> getArticleList(AppointmentCodeDTO.SearchCode search) {
																	
		List<AppointmentCode> list = appointmentService.getAppointentCodeList(search);  							
		
		List<AppointmentCodeDTO.SaveCode> dtoList = list.stream()
														.map(r -> AppointmentCodeDTO.SaveCode.convertDTO(r))
														.collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/appointmentcode/{id}"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getCode(@PathVariable(value="id") String id) {
		
		AppointmentCode code = appointmentService.getAppointmentCode(id);
					
		return WebControllerUtil.getResponse(code											
											,String.format("%d 건 조회되었습니다.", code == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/appointmentcode"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody AppointmentCodeDTO.SaveCode code, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveAppointmentCode(code);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/appointmentcodedetail")
	public ResponseEntity<?> getCodeDetailList(AppointmentCodeDTO.SearchCodeDetail dto) {
		 				
		List<AppointmentCodeDetail> list = appointmentService.getAppointmentCodeDetailList(dto);

		List<AppointmentCodeDTO.SaveCodeDetail> dtoList = list.stream()
															  .map(r -> AppointmentCodeDTO.convertDTO(r))
															  .collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/appointmentcodedetail/{id}/{detailId}")
	public ResponseEntity<?> getCodeDetail(@PathVariable(value="id") String id,
										   @PathVariable(value="detailId") String detailId) {
		 		
		AppointmentCodeDetail code = appointmentService.getAppointmentCodeDetail(id, detailId);
					
		AppointmentCodeDTO.SaveCodeDetail dto = AppointmentCodeDTO.convertDTO(code);
				
		return WebControllerUtil.getResponse(dto										
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/appointmentcodedetail"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCodeDetail(@RequestBody AppointmentCodeDTO.SaveCodeDetail dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveAppointmentCodeDetail(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/appointmentcodedetail")
	public ResponseEntity<?> delCodeDetail(@RequestBody AppointmentCodeDTO.SaveCodeDetail dto, BindingResult result) {						
												
		appointmentService.deleteAppointmentCodeDetail(dto);
								 						
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}	
		
	@GetMapping("/hrm/typelist")
	public ResponseEntity<?> getTypeList() {
		
		List<ChangeableTypeDTO.EnumDTO> list = new ArrayList<ChangeableTypeDTO.EnumDTO>();
		
		for (ChangeType menuType : ChangeType.values()) {			
			list.add(new EnumDTO(menuType.getCode(), menuType.getName()));
		}										
					
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/depttype/{code}")
	public ResponseEntity<?> getDeptType(@PathVariable(value="code") String code) {
		
		DeptTypeDTO.SaveCode deptType = appointmentService.getDeptTypeDTO(code);
					
		return WebControllerUtil.getResponse(deptType											
											,String.format("%d 건 조회되었습니다.", deptType == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/depttype"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveDeptType(@RequestBody DeptTypeDTO.SaveCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveDeptType(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
		
	@DeleteMapping("/hrm/depttype/{code}")
	public ResponseEntity<?> deleteDeptType(@PathVariable(value="code") String code) {				
																		
		appointmentService.deleteDeptType(code);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/jobtype/{code}")
	public ResponseEntity<?> getJobType(@PathVariable(value="code") String code) {
		
		JobTypeDTO.SaveCode jobType = appointmentService.getJobTypeDTO(code);
					
		return WebControllerUtil.getResponse(jobType
											,String.format("%d 건 조회되었습니다.", jobType == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/jobtype"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveJobType(@RequestBody JobTypeDTO.SaveCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveJobType(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}

	@DeleteMapping("/hrm/jobtype/{code}")
	public ResponseEntity<?> deleteJobType(@PathVariable(value="code") String code) {				
																		
		appointmentService.deleteJobType(code);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}	
	
	@GetMapping("/hrm/ledger/{id}")
	public ResponseEntity<?> getLedger(@PathVariable(value="id") String id) {
		
		Ledger ledger = appointmentService.getLedger(id);
					
		return WebControllerUtil.getResponse(ledger											
											,String.format("%d 건 조회되었습니다.", ledger == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/ledger"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveLedger(@RequestBody LedgerDTO.SaveLedger dto, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveLedger(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/ledger/{id}")
	public ResponseEntity<?> deleteLedger(@PathVariable(value="id") String id) {				
																		
		appointmentService.deleteLedger(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/ledgerlist/{id}")
	public ResponseEntity<?> getLedgerList(@PathVariable(value="id") String id) {
		
		Ledger ledger = appointmentService.getLedger(id);
					
		return WebControllerUtil.getResponse(ledger											
											,String.format("%d 건 조회되었습니다.", ledger == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/ledgerlist"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveLedgerList(@RequestBody LedgerDTO.SaveLedgerList dto, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveLedgerList(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
}
