package com.like.hrm.employee.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.like.file.infra.file.LocalFileRepository.FileUploadLocation;
import com.like.file.service.FileService;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.service.EmployeeService;

@Controller
public class EmployeeImageController {
	
	private FileService fileService;
		
	private EmployeeService employeeService;
		
	public EmployeeImageController(FileService fileService, EmployeeService employeeService) {
		this.fileService 		= fileService;
		this.employeeService 	= employeeService;
	}

	@PostMapping(value={"/hrm/employee/changeimage"})
	public ResponseEntity<?> changePassword(@RequestParam("file") MultipartFile file,
											@RequestParam("employeeId") String employeeId) throws Exception {				
		
		Map<String, Object> response = new HashMap<>();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);				
		
		String uuid = UUID.randomUUID().toString();
		String path = fileService.fileTransefer(file, uuid, FileUploadLocation.STATIC_PATH);
		
		Employee emp = employeeService.getEmployee(employeeId);
				
		emp.changeImagePath(uuid);
		
		employeeService.saveEmployee(emp);
		
		response.put("data", path);
		response.put("status", "done");
							
		return new ResponseEntity<Map<String,Object>>(response, responseHeaders, HttpStatus.OK);
	}
}
