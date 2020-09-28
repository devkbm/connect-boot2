package com.like.holiday.web;

import java.time.LocalDate;

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
import com.like.holiday.domain.model.Holiday;
import com.like.holiday.service.HolidayService;

@RestController
public class HolidayController {

	private HolidayService holidayService;	
	
	public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;		
	}
	
	@GetMapping("/com/holiday/{id}")
	public ResponseEntity<?> getCode(@PathVariable(value="id") LocalDate id) {
		
		Holiday entity = holidayService.getHoliyday(id);
					
		return WebControllerUtil.getResponse(entity											
											,String.format("%d 건 조회되었습니다.", entity == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/com/holiday"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody Holiday dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		holidayService.saveHoliday(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/com/holiday/{id}")
	public ResponseEntity<?> delCode(@PathVariable(value="id") LocalDate id) {						
												
		holidayService.deleteHoliday(id);
								 						
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
}
