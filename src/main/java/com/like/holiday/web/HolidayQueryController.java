package com.like.holiday.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.holiday.domain.model.DateInfo;
import com.like.holiday.domain.service.HolidayUtilService;

@RestController
public class HolidayQueryController {

	private HolidayUtilService holidayUtilService;
	
	public HolidayQueryController(HolidayUtilService holidayUtilService) {
		this.holidayUtilService = holidayUtilService;
	}
	
	@GetMapping("/common/holiday/{fromDate}/{toDate}")
	public ResponseEntity<?> getHolidayList(@PathVariable(value="fromDate") @DateTimeFormat(pattern="yyyyMMdd") LocalDate fromDate
										   ,@PathVariable(value="toDate") @DateTimeFormat(pattern="yyyyMMdd") LocalDate toDate) {
		
		List<DateInfo> list = holidayUtilService.getDateInfos(fromDate, toDate).getDates();			
					
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
}
