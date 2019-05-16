package com.like.user.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
import com.like.dept.domain.repository.DeptRepository;
import com.like.menu.domain.repository.MenuRepository;
import com.like.menu.service.MenuQueryService;
import com.like.user.domain.model.AuthenticationToken;
import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;
import com.like.user.domain.model.UserDTOAssembler;
import com.like.user.domain.repository.UserRepository;
import com.like.user.dto.AuthorityDTO;
import com.like.user.dto.LoginRequestDTO;
import com.like.user.dto.PasswordRequestDTO;
import com.like.user.dto.UserDTO;
import com.like.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
		
	@Autowired
	UserRepository userRepository;
	
	@Resource(name="menuJpaRepository")
	private MenuRepository menuRepository;
	
	@Resource(name="deptJpaRepository")
	private DeptRepository deptRepository;	
			
	@Resource
	UserService userService;
	
	@Resource
	MenuQueryService menuQueryService;	
		
	@GetMapping(value={"/common/user/{id}"})
	public ResponseEntity<?> getUser(@PathVariable(value="id") String userId) throws FileNotFoundException, IOException {
						
		User user = userService.getUser(userId);				
		
		UserDTO.UserSave dto = UserDTOAssembler.convertDTO(user);					
		
		return WebControllerUtil.getResponse(dto,
				 user == null ? 0 : 1, 
				 user == null ? false : true,
				 "조회 되었습니다.",
				 HttpStatus.OK);
	}
		
	@GetMapping(value={"/common/user"})
	public ResponseEntity<?> getUserList(UserDTO.QueryCondition condition) {
				
		List<User> userList = userService.getUserList(condition);						
		
		return WebControllerUtil.getResponse(userList,
				userList.size(), 
				userList.size() > 0 ? true : false ,
				"조회 되었습니다.",
				HttpStatus.OK);
	}
	
	@PostMapping(value={"/common/user"})	
	public ResponseEntity<?> saveUser(@RequestBody UserDTO.UserSave dto, BindingResult result) {
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		}						
				
		User user = UserDTOAssembler.toEntity(dto, userRepository, menuRepository, deptRepository);
											
		userService.createUser(user);					
																					 		
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}	
	
	@DeleteMapping(value={"/common/user/{id}"})
	public ResponseEntity<?> deleteUser(@PathVariable(value="id") String userId) {
										
		userService.deleteUser(userId);															
								 					
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
		
	@PostMapping(value={"/common/user/{id}/changePassword"})
	public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDTO dto) {				
						
		userService.changePassword(dto.getUserId(), dto.getBeforePassword(), dto.getAfterPassword());													
								 					
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				"비밀번호가 변경되었습니다.", 
				HttpStatus.OK);
	}
			
	@PostMapping(value={"/common/user/{id}/initPassword"})
	public ResponseEntity<?> initializePassword(@PathVariable(value="id") String userId) {			
				
		userService.initPassword(userId);														
								 					
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				"비밀번호가 초기화되었습니다.", 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/common/authority"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getAuthorityList(AuthorityDTO.QueryCondition dto) {				
		
		List<Authority> authorityList = userService.getAuthorityList(dto);								 				
		
		return WebControllerUtil.getResponse(authorityList,				
				authorityList.size(), 
				authorityList.size() > 0 ? true : false ,
				"조회 되었습니다.",
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/common/authority/{id}"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getAuthority(@PathVariable(value="id") String authorityName) {			
		
		Authority authority = userService.getAuthority(authorityName);										
		
		return WebControllerUtil.getResponse(authority,
				 authority == null ? 0 : 1, 
				 authority == null ? false : true,
				 "조회 되었습니다.",
				 HttpStatus.OK);
	}
	
	@RequestMapping(value={"/common/authority"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveAuthority(@RequestBody AuthorityDTO.AuthoritySave dto, BindingResult result) {
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		}
		
		Authority authority = userService.getAuthority(dto.getAuthority());
		
		if (authority == null) {
			authority = new Authority(dto.getAuthority(), dto.getDescription());
		} 
		
		userService.createAuthority(authority);					
																				 				
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}	
	
	@DeleteMapping("/common/authority/{id}")
	public ResponseEntity<?> deleteAuthority(@PathVariable(value="id") String authorityName) {
		
		userService.deleteAuthority(authorityName);					
			
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
			
}
