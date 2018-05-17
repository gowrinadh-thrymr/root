package com.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.ApplicationUser;
import com.test.repo.ApplicationUserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(ApplicationUserRepository applicationUserRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@GetMapping("/greet")
	public String getUsers() {
		return "Working";

	}
	
	@PostMapping("/sign-up")
	public void signUp(@RequestBody ApplicationUser user) {
		user.setUsername(user.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		applicationUserRepository.save(user);
	}

	@PostMapping(value = "/login")
	public void name(HttpServletRequest req,HttpServletResponse response) {
		System.out.println(req.getParameter("username"));
	}
}