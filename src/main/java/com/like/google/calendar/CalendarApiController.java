package com.like.google.calendar;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.like.user.domain.model.AuthenticationToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CalendarApiController {

	private RestTemplateBuilder restTemplateBuilder;
	
	public CalendarApiController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplateBuilder = restTemplateBuilder;
	}
	
	/**
	 * https://developers.google.com/calendar/v3/reference/calendarList/list
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping("/calendarlist")
    public ResponseEntity<?> getTest(@SessionAttribute("user") AuthenticationToken token) {
    	RestTemplate restTemplate = this.restTemplateBuilder.build();
    	//AuthenticationToken token = SessionUtil.getAuthenticationToken(session);
    	
    	MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    	//headers.add("X-XSRF-HEADER", "BlahBlah");
    	headers.add("Authorization", "Bearer " + token.getOAuthAccessToken());
    	
    	HttpEntity<?> request = new HttpEntity<>(headers);
    	Object dd = restTemplate.exchange("https://www.googleapis.com/calendar/v3/users/me/calendarList", HttpMethod.GET, request, Object.class);
    	    	    	    
    	return new ResponseEntity<Object>(dd, HttpStatus.OK);
    }
    
    @GetMapping("/calendars/{id}/events")
    public ResponseEntity<?> getTest2(@SessionAttribute("user") AuthenticationToken token
    								 ,@PathVariable String id
    								 ,@RequestParam(required = false, value = "nextPageToken") String nextPageToken) {
    	RestTemplate restTemplate = this.restTemplateBuilder.build();
    	//AuthenticationToken token = SessionUtil.getAuthenticationToken(session);
    	
    	MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    	//headers.add("X-XSRF-HEADER", "BlahBlah");
    	headers.add("Authorization", "Bearer " + token.getOAuthAccessToken());
    	
    	UriComponentsBuilder builder = UriComponentsBuilder    		    			
    									.fromHttpUrl("https://www.googleapis.com/calendar/v3/calendars/"+id+"/events")    									
    									.queryParam("pageToken",nextPageToken);
    									
    	
    	log.info(builder.toString());
    	
    	HttpEntity<?> request = new HttpEntity<>(headers);
    	Object dd = restTemplate.exchange(builder.build(false).toUriString(), HttpMethod.GET, request, Object.class);
    	    	    	    
    	return new ResponseEntity<Object>(dd, HttpStatus.OK);
    	
    }
	
}
