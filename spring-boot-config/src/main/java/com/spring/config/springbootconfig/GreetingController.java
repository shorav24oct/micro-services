package com.spring.config.springbootconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@Value("${my.greetings}")
	private String greetingMessage;
	
	@GetMapping("/greeting")
	public String getGreetings() {
		return greetingMessage;
	}

}
