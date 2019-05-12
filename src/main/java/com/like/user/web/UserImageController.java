package com.like.user.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.like.file.domain.model.FileInfo;
import com.like.file.infra.file.LocalFileRepository.FileUploadLocation;
import com.like.file.service.FileService;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;

@Controller
public class UserImageController {

	@Resource(name = "fileService")
	private FileService fileService;
	
	@Resource
	UserService userService;
	
	@PostMapping(value={"/user/image"})
	public ResponseEntity<?> changePassword(@RequestParam("file") MultipartFile file,
											@RequestParam("userId") String userId) throws Exception {				
		
		Map<String, Object> response = new HashMap<>();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
				
		//FileInfo fileInfo = fileService.uploadFile(file, userId, "user");
		
		String uuid = UUID.randomUUID().toString();
		fileService.fileTransefer(file, uuid, FileUploadLocation.STATIC_PATH);
		
		User user = userService.getUser(userId);
		
		
		//user.ChangeImage(fileInfo);
		
		userService.saveUser(user);
		
		//response.put("data", fileInfo);
		response.put("status", "done");
							
		return new ResponseEntity<Map<String,Object>>(response, responseHeaders, HttpStatus.OK);
	}
}
