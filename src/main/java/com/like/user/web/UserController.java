package com.like.user.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import com.like.common.util.SessionUtil;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.user.boundary.AuthorityDTO;
import com.like.user.boundary.PasswordRequestDTO;
import com.like.user.boundary.UserDTO;
import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;

@RestController
public class UserController {		
				
	private UserService userService;
		
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value={"/common/user/myinfo"})
	public ResponseEntity<?> getUserInfo() throws FileNotFoundException, IOException {
								
		String userId = SessionUtil.getUserId();
				
		User user = userService.getUser(userId);				
		
		UserDTO.SaveUser dto = UserDTO.convertDTO(user);					
		
		return WebControllerUtil
				.getResponse(dto
							,user == null ? 0 : 1
							,user == null ? false : true
							,"조회 되었습니다."
							,HttpStatus.OK);
	}
	
	@GetMapping(value={"/common/user/{id}"})
	public ResponseEntity<?> getUser(@PathVariable(value="id") String userId) throws FileNotFoundException, IOException {
						
		User user = userService.getUser(userId);				
		
		UserDTO.SaveUser dto = UserDTO.convertDTO(user);					
		
		return WebControllerUtil
				.getResponse(dto
							,user == null ? 0 : 1
							,user == null ? false : true
							,"조회 되었습니다."
							,HttpStatus.OK);
	}
		
	@GetMapping(value={"/common/user"})
	public ResponseEntity<?> getUserList(UserDTO.SearchUser condition) throws FileNotFoundException, IOException {
				
		List<User> userList = userService.getUserList(condition);						
		
		List<UserDTO.SaveUser> dtoList = new ArrayList<>();
		
		for (User user : userList) {
			dtoList.add(UserDTO.convertDTO(user));
		}
		
		return WebControllerUtil
				.getResponse(dtoList
							,dtoList.size()
							,dtoList.size() > 0 ? true : false 
							,"조회 되었습니다."
							,HttpStatus.OK);
	}
	
	@PostMapping(value={"/common/user"})	
	public ResponseEntity<?> saveUser(@RequestBody UserDTO.SaveUser dto, BindingResult result) {
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		}										
											
		userService.createUser(dto);					
																					 		
		return WebControllerUtil
				.getResponse(null
							,1
							,true
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}	
	
	@DeleteMapping(value={"/common/user/{id}"})
	public ResponseEntity<?> deleteUser(@PathVariable(value="id") String userId) {
										
		userService.deleteUser(userId);															
								 					
		return WebControllerUtil
				.getResponse(null
							,1
							,true
							,String.format("%d 건 삭제되었습니다.", 1)
							,HttpStatus.OK);
	}
		
	@PostMapping(value={"/common/user/{id}/changePassword"})
	public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDTO dto) {				
						
		userService.changePassword(dto.getUserId(), dto.getBeforePassword(), dto.getAfterPassword());													
								 					
		return WebControllerUtil
				.getResponse(null
							,1
							,true
							,"비밀번호가 변경되었습니다."
							,HttpStatus.OK);
	}
			
	@PostMapping(value={"/common/user/{id}/initPassword"})
	public ResponseEntity<?> initializePassword(@PathVariable(value="id") String userId) {			
				
		userService.initPassword(userId);														
								 					
		return WebControllerUtil
				.getResponse(null
							,1
							,true
							,"비밀번호가 초기화되었습니다."
							,HttpStatus.OK);
	}	
			
}
