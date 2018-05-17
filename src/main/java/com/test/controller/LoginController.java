package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/secure")
public class LoginController {

	@GetMapping("/greet")
	public String getSecured() {
		return "Secured Working";

	}

}
