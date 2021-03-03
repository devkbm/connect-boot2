package com.like.hrm.appointment.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.boundary.LedgerDTO.ChangeInfo;
import com.like.hrm.appointment.boundary.LedgerDTO.QueryLedgerList;
import com.like.hrm.appointment.boundary.LedgerDTO.SaveLedgerList;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.service.AppointmentCodeQueryService;
import com.like.hrm.appointment.service.AppointmentQueryService;
import com.like.hrm.appointment.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AppointmentListController {

	private AppointmentService appointmentService;	
	
	private AppointmentQueryService appointmentQueryService;
	
	private AppointmentCodeQueryService appointmentCodeQueryService;
	
	public AppointmentListController(AppointmentService appointmentService
			,AppointmentQueryService appointmentQueryService
			,AppointmentCodeQueryService appointmentCodeQueryService) {
		this.appointmentService = appointmentService;
		this.appointmentQueryService = appointmentQueryService;
		this.appointmentCodeQueryService = appointmentCodeQueryService;
	}
	
	@GetMapping("/hrm/ledger/list")
	public ResponseEntity<?> getLedgerList(LedgerDTO.SearchLedgerList dto) {
		
		//List<LedgerList> list = appointmentQueryService.getLedgerList(dto);
		List<QueryLedgerList> list = appointmentQueryService.getLedgerListDTO(dto);
					
		//SaveLedgerList rtn = LedgerDTO.convertDTO(list);
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/ledger/{ledgerId}/list/{listId}")
	public ResponseEntity<?> getLedgerList(@PathVariable(value="ledgerId") String ledgerId
			   							  ,@PathVariable(value="listId") String listId) {
		
		LedgerList list = appointmentService.getLedgerList(ledgerId, listId);
					
		SaveLedgerList rtn = null; 
		
		if (list != null) {
			rtn = LedgerDTO.SaveLedgerList.convert(list);
		} else {
			rtn = new SaveLedgerList();
		}
		
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
	
	//@GetMapping("/hrm/ledger/{id}/list/{id2}/appoint")
	@PostMapping(value={"/hrm/ledger/{ledgerId}/list/{listId}/appoint"})
	public ResponseEntity<?> appointProcess(@PathVariable(value="ledgerId") String ledgerId
            							   ,@PathVariable(value="listId") String listId) {
		
		
		appointmentService.appoint(ledgerId, listId);
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 처리되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/ledger/{ledgerId}/list/{listId}")
	public ResponseEntity<?> deleteLedgerList(@PathVariable(value="ledgerId") String ledgerId
			   								 ,@PathVariable(value="listId") String listId) {				
																		
		appointmentService.deleteLedgerList(ledgerId, listId);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/ledger/list/changeinfo/{code}")
	public ResponseEntity<?> getCodeDetailList(@PathVariable(value="code") String code) {
		
		List<ChangeInfo> list = appointmentCodeQueryService.getChangeInfoList(code);			
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
}
