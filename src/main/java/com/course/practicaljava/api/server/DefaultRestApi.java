package com.course.practicaljava.api.server;

import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "Default Rest API", description = "Documentation for Default Rest API")
public class DefaultRestApi {

	private Logger LOG = LoggerFactory.getLogger(DefaultRestApi.class);

	@GetMapping(value = "/welcome")
	@Operation(summary = "Welcome", description = "Description for welcome API")
	public String welcome() {
		LOG.info(StringUtils.join("Hello", " this is", " Spring boot", " REST API"));
		return "Welcome to Spring Boot";
	}

	@GetMapping(value = "/time")
	public String time() {
		return LocalTime.now().toString();
	}

	@GetMapping(value = "/header-one")
	public String headerByAnnotation(@RequestHeader(name = "User-agent") String headerUserAgent,
			@RequestHeader(name = "Practical-java", required = false) String headerPracticalJava) {
		return "User-agent : " + headerUserAgent + ", Practical-java : " + headerPracticalJava;
	}

	@GetMapping(value = "/header-two")
	public String headerByRequest(ServerHttpRequest request) {
		return "User-agent : " + request.getHeaders().getValuesAsList("User-agent") + ", Practical-java : "
				+ request.getHeaders().getValuesAsList("Practical-java");
	}

}
