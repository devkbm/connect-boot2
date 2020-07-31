package com.like.hrm.duty.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class DutyApplication {
	
	private Long dutyId;
	
	private String employeeId;
	
	private String dutyCode;
	
	private String dutyReason;
	
	private LocalDateTime dutyStartDateTime;
	
	private LocalDateTime dutyEndDateTime;
	
	private List<DutyApplicationAttachedFile> fileList;
	
	public DutyApplication(String employeeId
			, String dutyCode
			, String dutyReason
			, LocalDateTime dutyStartDateTime
			,LocalDateTime dutyEndDateTime) {
		this.employeeId = employeeId;
		this.dutyCode = dutyCode;
		this.dutyReason = dutyReason;
		this.dutyStartDateTime = dutyStartDateTime;
		this.dutyEndDateTime = dutyEndDateTime;
	}	
	
	public void addFile(DutyApplicationAttachedFile file) {
		this.fileList.add(file);
	}
	
}
