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
	
	@GetMapping("/hrm/appointmentlist")
	public ResponseEntity<?> getList(AppointmentListDTO.SearchAppointmentList dto) {
				
		List<QueryAppointmentList> list = queryService.getListDTO(dto);						
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/appointmentlist/{listId}")
	public ResponseEntity<?> getAppointmentList(@PathVariable(value="listId") Long listId) {
		
		AppointmentList list = commandService.getAppointmentList(listId);
					
		SaveAppointmentList rtn = null; 
		
		if (list == null) {
			rtn = new SaveAppointmentList();
		} else {
			rtn = AppointmentListDTO.SaveAppointmentList.convert(list);
		}
				
		return WebControllerUtil.getResponse(rtn											
											,String.format("%d 건 조회되었습니다.", rtn != null ? 1 : 0)
											,HttpStatus.OK);
	}
		
	@RequestMapping(value={"/hrm/appointmentlist"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveAppointmentList(@RequestBody AppointmentListDTO.SaveAppointmentList dto, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		commandService.saveAppointmentList(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/appointmentlist/{listId}")
	public ResponseEntity<?> deleteAppointmentList(@PathVariable(value="listId") Long listId) {				
																		
		commandService.deleteAppointmentList(listId);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	//@GetMapping("/hrm/ledger/{id}/list/{id2}/appoint")
	@PostMapping(value={"/hrm/appointmentlist/{listId}/appoint"})
	public ResponseEntity<?> appointProcess(@PathVariable(value="listId") Long listId) {
		
		
		commandService.appoint(listId);
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 처리되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	
	@GetMapping("/hrm/appointmentlist/changeinfo/{code}")
	public ResponseEntity<?> getCodeDetailList(@PathVariable(value="code") String code) {
		
		List<AppointmentListDTO.ChangeInfo> list = appointmentCodeQueryService.getChangeInfoList(code);			
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
}
