package com.like.user.web;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.util.SessionUtil;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.user.boundary.PasswordRequestDTO;
import com.like.user.boundary.UserDTO;
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
														
		User user = userService.getUser(SessionUtil.getUserId());				
		
		UserDTO.SaveUser dto = UserDTO.convertDTO(user);					
		
		return WebControllerUtil
				.getResponse(dto							
							,String.format("%d 건 조회되었습니다.", 1)
							,HttpStatus.OK);
	}
	
	@GetMapping(value={"/common/user/{id}"})
	public ResponseEntity<?> getUser(@PathVariable(value="id") String userId) throws FileNotFoundException, IOException {
						
		User user = userService.getUser(userId);				
		
		UserDTO.SaveUser dto = UserDTO.convertDTO(user);					
		
		return WebControllerUtil
				.getResponse(dto							
							,String.format("%d 건 조회되었습니다.", 1)
							,HttpStatus.OK);
	}
		
	
	
	@PostMapping(value={"/common/user"})	
	public ResponseEntity<?> saveUser(@RequestBody UserDTO.SaveUser dto, BindingResult result) {
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		}										
											
		userService.saveUser(dto);					
																					 		
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}	
	
	@DeleteMapping(value={"/common/user/{id}"})
	public ResponseEntity<?> deleteUser(@PathVariable(value="id") String userId) {
										
		userService.deleteUser(userId);															
								 					
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 삭제되었습니다.", 1)
							,HttpStatus.OK);
	}
		
	@PostMapping(value={"/common/user/{id}/changePassword"})
	public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDTO dto) {				
						
		userService.changePassword(dto.getUserId(), dto.getBeforePassword(), dto.getAfterPassword());													
								 					
		return WebControllerUtil
				.getResponse(null							
							,"비밀번호가 변경되었습니다."
							,HttpStatus.OK);
	}
			
	@PostMapping(value={"/common/user/{id}/initPassword"})
	public ResponseEntity<?> initializePassword(@PathVariable(value="id") String userId) {			
				
		userService.initPassword(userId);														
								 					
		return WebControllerUtil
				.getResponse(null							
							,"비밀번호가 초기화되었습니다."
							,HttpStatus.OK);
	}	
			
}
