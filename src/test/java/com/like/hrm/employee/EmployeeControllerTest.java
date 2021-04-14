package com.like.hrm.employee;

import static org.hamcrest.CoreMatchers.anything;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.Collections;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.like.core.web.response.ResponseObject;
import com.like.hrm.employee.domain.model.Employee;
import com.like.user.boundary.LoginRequestDTO;
import com.like.user.domain.model.AuthenticationToken;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
		
	@Autowired
	TestRestTemplate testRestTemplate;			
		 	 	 
	@Before 
    public void setUp() { 
//		/*User user = userService.getUser("1");
//		Optional<String> username = Optional.of(user.getUsername());
//		Mockito.when(auditorAware.getCurrentAuditor()).thenReturn(username);*/	
//		/*
//		LoginRequestDTO dto = LoginRequestDTO.builder().username("1").password("1234").build();			
//		
//		System.out.println(dto.toString());
//		ResponseEntity<?> page = testRestTemplate.postForEntity("/common/user/login", dto, AuthenticationToken.class);
//		//System.out.println(token);
//		
//		testRestTemplate.getRestTemplate().setInterceptors(
//	                Collections.singletonList((request, body, execution) -> {
//	                    HttpHeaders headers = request.getHeaders();
//	                    
//	                    String cookie = page.getHeaders().get("Set-Cookie").get(0);
//	            		headers.set("Cookie", cookie);
//	            		headers.add("Content-Type", "application/json");
//	            		
//	                    /*
//	                    String xsrf_token = page.getHeaders().get("Set-Cookie").get(1);	                    	                    	                    	                    	                
//	            		System.out.println(cookie);
//	            		System.out.println(page.getHeaders());
//	            		System.out.println(xsrf_token);
//	            		System.out.println(xsrf_token.substring(xsrf_token.indexOf("=")+1,xsrf_token.lastIndexOf(";")));
//	            		*/
//	            		
//	            		//Matcher matcher = Pattern.compile("(?s).*name=\"_csrf\".*?value=\"([^\"]+).*").matcher((CharSequence) page.getBody());
//	            		//assertTrue("No csrf token: " + page.getBody(), matcher.matches());
//	            		//headers.set("X-CSRF-TOKEN", matcher.group(1));	            		
//	            		//headers.set("X-CSRF-TOKEN", xsrf_token.substring(xsrf_token.indexOf("=")+1,xsrf_token.lastIndexOf(";")));
//	            		//headers.set("XSRF-TOKEN", xsrf_token.substring(xsrf_token.indexOf("=")+1,xsrf_token.lastIndexOf(";")));
//	                    
//	                    return execution.execute(request, body);
//	                }));
	                
	                
    } 
    
	
	@Test
	public void 사원조회001() {								
		//testRestTemplate = new TestRestTemplate("1","1234", TestRestTemplate.HttpClientOption.ENABLE_COOKIES);		
		
		//setUp();
		//given					
		/*
		String empId = "20200001";			
		
		ResponseObject<?> sample = new ResponseObject(new Employee("2020", "11", "11", "11", "11"),0,false, ""); 
			
		//when
		ResponseEntity<?> empl = testRestTemplate.getForEntity("/hrm/employee/20200001", sample.getClass());
		
		//then
		System.out.println(((ResponseObject<Employee>)empl.getBody()).getStatus());
		*/
	}
}
