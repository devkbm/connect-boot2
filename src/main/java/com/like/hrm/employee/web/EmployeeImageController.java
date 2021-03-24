package com.like.hrm.employee.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.like.file.infra.file.LocalFileRepository.FileUploadLocation;
import com.like.file.service.FileService;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmployeeImageController {
	
	private EmployeeService employeeService;
	
	private FileService fileService;
				
	public EmployeeImageController(EmployeeService employeeService,
								   FileService fileService) {
		this.fileService 		= fileService;
		this.employeeService 	= employeeService;
	}

	@PostMapping(value={"/hrm/employee/changeimage"})
	public ResponseEntity<?> changeEmployeeImage(@RequestParam("file") MultipartFile file,
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
	
	@RequestMapping(value={"/hrm/employee/downloadimage"}, method=RequestMethod.GET) 
	public HttpServletResponse downloadEmployeeImage(HttpServletResponse response,
													 @RequestParam("employeeId") String employeeId) throws Exception {
				
		Employee emp = employeeService.getEmployee(employeeId);			
		
		File file = fileService.getStaticPathFile(emp.getImagePath());
				
		response = this.setResponse(response, file.length(), employeeId);
		
		fileService.downloadFile(file, response);
			
		return response;
	}
	
	private HttpServletResponse setResponse(HttpServletResponse response, long fileSize, String fileName) throws Exception {
		
		// get MIME type of the file
		String mimeType= null;
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";	         
		}
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLengthLong(fileSize);
		response.setCharacterEncoding("UTF-8");
		
		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment;filename=\"%s\"", new String(fileName.getBytes("UTF-8"), "8859_1"));
		
		response.setHeader(headerKey, headerValue);
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");	
		
		return response;
	}
}
