package com.skilldistillery.trailnutz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.trailnutz.entities.User;
import com.skilldistillery.trailnutz.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4210"})

public class UserController {
	
	@Autowired
	private UserService userSvc;
	
	@GetMapping("users/{username}")
	public User getUserByUsername(@PathVariable String username) {
		
		User user = userSvc.userByUsername(username);
		
		return user;
	}
}