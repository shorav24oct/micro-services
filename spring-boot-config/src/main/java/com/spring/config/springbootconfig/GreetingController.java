package com.spring.config.springbootconfig;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@Value("${my.greetings: default value}")
	private String greetingMessage;

	@Value("default static message ")
	private String staticMessage;

	@Value("${my.list.values}")
	private List<String> listValues;
	
	@Autowired
	DBConfig dbConfig;

	@GetMapping("/greeting")
	public String getGreetings() {
		return dbConfig.getConnection() + dbConfig.getHost() + dbConfig.getPort();
	}

}
