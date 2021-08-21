package com.skilldistillery.trailnutz.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("user")
	public User getUserByUsername(Principal principal) {
		
		User user = userSvc.userByUsername(principal.getName());
		
		return user;
	}
	
	@PutMapping("user")
	public User updateUser(@RequestBody User user, Principal principal, HttpServletResponse res) {
		User updateUser = userSvc.updateUser(user, principal.getName());
		
		if(updateUser == null) {
			res.setStatus(400);
		}
		return updateUser;
	}
	
	@DeleteMapping("user/{userId}")
	public void disableUser(@PathVariable int userId, Principal principal, HttpServletResponse res) {
		boolean isDisabled = userSvc.disableUser(userId, principal.getName());
		if(isDisabled) {
			res.setStatus(204);
		}else {
			res.setStatus(400);
		}
	}
	
	@PutMapping("user/{userId}")
	public void enableUser(@PathVariable int userId, Principal principal, HttpServletResponse res) {
		boolean isEnabled = userSvc.userEnable(userId, principal.getName());
		if(isEnabled) {
			res.setStatus(200);
		}else {
			res.setStatus(400);
		}
	}
	
	
}
