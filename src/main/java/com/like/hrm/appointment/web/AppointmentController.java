package com.like.hrm.appointment.web;

import javax.annotation.Resource;

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
import com.like.hrm.appointment.boundary.DeptTypeDTO;
import com.like.hrm.appointment.boundary.JobTypeDTO;
import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.DeptType;
import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AppointmentController {

	@Resource
	private AppointmentService appointmentService;	

	@RequestMapping(value={"/hrm/appointmentcode/{id}"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getCode(@PathVariable(value="id") String id) {
		
		AppointmentCode code = appointmentService.getAppointmentCode(id);
					
		return WebControllerUtil.getResponse(code
											,code == null ? 0 : 1
											,true
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
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/appointmentcodedetail"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCodeDetail(@RequestBody AppointmentCodeDTO.SaveCodeDetail dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveAppointmentCodeDetail(dto);						
								 					
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/appointmentcodedetail")
	public ResponseEntity<?> delCodeDetail(@RequestBody AppointmentCodeDTO.SaveCodeDetail dto, BindingResult result) {						
												
		appointmentService.deleteAppointmentCodeDetail(dto);
								 						
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}	
		
	@GetMapping("/hrm/depttype/{code}")
	public ResponseEntity<?> getDeptType(@PathVariable(value="code") String code) {
		
		DeptType deptType = appointmentService.getDeptType(code);
					
		return WebControllerUtil.getResponse(deptType
											,deptType == null ? 0 : 1
											,true
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
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
		
	@DeleteMapping("/hrm/depttype/{code}")
	public ResponseEntity<?> deleteDeptType(@PathVariable(value="code") String code) {				
																		
		appointmentService.deleteDeptType(code);						
								 					
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/jobtype/{code}")
	public ResponseEntity<?> getJobType(@PathVariable(value="code") String code) {
		
		JobType jobType = appointmentService.getJobType(code);
					
		return WebControllerUtil.getResponse(jobType
											,jobType == null ? 0 : 1
											,true
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
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}

	@DeleteMapping("/hrm/jobtype/{code}")
	public ResponseEntity<?> deleteJobType(@PathVariable(value="code") String code) {				
																		
		appointmentService.deleteJobType(code);						
								 					
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}	
	
	@GetMapping("/hrm/ledger/{id}")
	public ResponseEntity<?> getLedger(@PathVariable(value="id") String id) {
		
		Ledger ledger = appointmentService.getLedger(id);
					
		return WebControllerUtil.getResponse(ledger
											,ledger == null ? 0 : 1
											,true
											,String.format("%d 건 조회되었습니다.", ledger == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/ledger"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveLedger(@RequestBody LedgerDTO.SaveCode dto, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveLedger(dto);						
								 					
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/ledger/{id}")
	public ResponseEntity<?> deleteLedger(@PathVariable(value="id") String id) {				
																		
		appointmentService.deleteLedger(id);						
								 					
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
}
