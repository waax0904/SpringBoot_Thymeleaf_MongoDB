package com.sts.test.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/test" ,  produces = MediaType.APPLICATION_JSON_VALUE)
public class RestfulController {

	// Get value from profile setting 
	@Value("${test.value}")
	private String hello; 

	@GetMapping
	public String printValue() throws NullPointerException {
		System.out.println(hello);
		return hello; 
	}
}
