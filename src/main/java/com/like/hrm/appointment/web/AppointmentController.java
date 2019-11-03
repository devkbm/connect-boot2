package com.like.hrm.appointment.web;

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
import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.boundary.LedgerDTO.ChangeInfo;
import com.like.hrm.appointment.boundary.LedgerDTO.SaveLedgerList;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.service.AppointmentQueryService;
import com.like.hrm.appointment.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AppointmentController {

	private AppointmentService appointmentService;	
	
	private AppointmentQueryService appointmentQueryService;

	public AppointmentController(AppointmentService appointmentService
								,AppointmentQueryService appointmentQueryService) {
		this.appointmentService = appointmentService;
		this.appointmentQueryService = appointmentQueryService;
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
	
	@GetMapping("/hrm/ledger/list")
	public ResponseEntity<?> getLedgerList2(LedgerDTO.SearchLedgerList dto) {
		
		LedgerList list = appointmentQueryService.getLedgerList(dto);
					
		SaveLedgerList rtn = LedgerDTO.convertDTO(list);
		
		return WebControllerUtil.getResponse(rtn											
											,String.format("%d 건 조회되었습니다.", rtn != null ? 1 : 0)
											,HttpStatus.OK);
	}
		
	@RequestMapping(value={"/hrm/ledger/list"}, method={RequestMethod.POST,RequestMethod.PUT}) 
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
	
	@DeleteMapping("/hrm/ledger/{id}/list/{id2}")
	public ResponseEntity<?> deleteLedgerList(@PathVariable(value="id") String id
			                                 ,@PathVariable(value="id2") String id2 ) {				
																		
		appointmentService.deleteLedgerList(id, id2);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/ledger/list/changeinfo/{code}")
	public ResponseEntity<?> getCodeDetailList(@PathVariable(value="code") String code) {
		
		List<ChangeInfo> list = appointmentService.getChangeInfoList(code);
		
		//log.info(list.toString());
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
}
