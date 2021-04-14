package com.like.user.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.user.boundary.UserDTO;
import com.like.user.domain.model.User;
import com.like.user.service.UserQueryService;

@RestController
public class UserQueryController {

	private UserQueryService service;
	
	public UserQueryController(UserQueryService service) {
		this.service = service;
	}
	
	@GetMapping(value={"/common/user"})
	public ResponseEntity<?> getUserList(UserDTO.SearchUser condition) throws FileNotFoundException, IOException {
				
		List<User> userList = service.getUserList(condition);						
		
		List<UserDTO.SaveUser> dtoList = new ArrayList<>();
		
		for (User user : userList) {
			dtoList.add(UserDTO.convertDTO(user));
		}
		
		return WebControllerUtil
				.getResponse(dtoList							
							,String.format("%d 건 조회되었습니다.", dtoList.size())
							,HttpStatus.OK);
	}
}
