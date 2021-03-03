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
import com.like.hrm.appointment.boundary.AppointmentListDTO;
import com.like.hrm.appointment.boundary.AppointmentListDTO.SaveAppointmentList;
import com.like.hrm.appointment.boundary.QueryAppointmentList;
import com.like.hrm.appointment.domain.model.AppointmentList;
import com.like.hrm.appointment.service.AppointmentCodeQueryService;
import com.like.hrm.appointment.service.AppointmentListCommandService;
import com.like.hrm.appointment.service.AppointmentListQueryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AppointmentListController {

	private AppointmentListCommandService commandService;	
	
	private AppointmentListQueryService queryService;
	
	private AppointmentCodeQueryService appointmentCodeQueryService;
	
	public AppointmentListController(AppointmentListCommandService commandService
			,AppointmentListQueryService queryService
			,AppointmentCodeQueryService appointmentCodeQueryService) {
		this.commandService = commandService;
		this.queryService = queryService;
		this.appointmentCodeQueryService = appointmentCodeQueryService;
	}
	
	@GetMapping("/hrm/ledger/list")
	public ResponseEntity<?> getList(AppointmentListDTO.SearchAppointmentList dto) {
		
		//List<LedgerList> list = appointmentQueryService.getLedgerList(dto);
		List<QueryAppointmentList> list = queryService.getListDTO(dto);
					
		//SaveLedgerList rtn = LedgerDTO.convertDTO(list);
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/ledger/{ledgerId}/list/{listId}")
	public ResponseEntity<?> getLedgerList(@PathVariable(value="ledgerId") String ledgerId
			   							  ,@PathVariable(value="listId") String listId) {
		
		AppointmentList list = commandService.getLedgerList(ledgerId, listId);
					
		SaveAppointmentList rtn = null; 
		
		if (list != null) {
			rtn = AppointmentListDTO.SaveAppointmentList.convert(list);
		} else {
			rtn = new SaveAppointmentList();
		}
		
		return WebControllerUtil.getResponse(rtn											
											,String.format("%d 건 조회되었습니다.", rtn != null ? 1 : 0)
											,HttpStatus.OK);
	}
		
	@RequestMapping(value={"/hrm/ledger/list"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveLedgerList(@RequestBody AppointmentListDTO.SaveAppointmentList dto, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		commandService.saveLedgerList(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	//@GetMapping("/hrm/ledger/{id}/list/{id2}/appoint")
	@PostMapping(value={"/hrm/ledger/{ledgerId}/list/{listId}/appoint"})
	public ResponseEntity<?> appointProcess(@PathVariable(value="ledgerId") String ledgerId
            							   ,@PathVariable(value="listId") String listId) {
		
		
		commandService.appoint(ledgerId, listId);
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 처리되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/ledger/{ledgerId}/list/{listId}")
	public ResponseEntity<?> deleteLedgerList(@PathVariable(value="ledgerId") String ledgerId
			   								 ,@PathVariable(value="listId") String listId) {				
																		
		commandService.deleteLedgerList(ledgerId, listId);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/ledger/list/changeinfo/{code}")
	public ResponseEntity<?> getCodeDetailList(@PathVariable(value="code") String code) {
		
		List<AppointmentListDTO.ChangeInfo> list = appointmentCodeQueryService.getChangeInfoList(code);			
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
}
