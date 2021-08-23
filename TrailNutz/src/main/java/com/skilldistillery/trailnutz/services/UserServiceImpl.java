package com.skilldistillery.trailnutz.services;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.skilldistillery.trailnutz.entities.User;
import com.skilldistillery.trailnutz.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	PasswordEncoder encoder;

	@Override
	public User userByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User updateUser(User user, String username) {
		User managedUser;
		try {
			managedUser = userRepo.findByUsername(username);
			if(user.getId() == managedUser.getId()) {
				managedUser.setFavoriteTrailFood(user.getFavoriteTrailFood());
				managedUser.setImageUrl(user.getImageUrl());
				managedUser.setFirstName(user.getFirstName());
				managedUser.setLastName(user.getLastName());
				if(user.getPassword().length() < 40) {
					if(!encoder.matches(user.getPassword(), managedUser.getPassword())) {
						managedUser.setPassword(encoder.encode(user.getPassword()));
					}
				}
				userRepo.saveAndFlush(managedUser);
			}
		} catch (Exception e) {
			managedUser = null;
		}
		return managedUser;
	}

	@Override
	public boolean disableUser(int userId, String username) {
		try {
			User admin = userRepo.findByUsername(username);
			if(admin.getRole().equalsIgnoreCase("admin")) {
				Optional<User> user = userRepo.findById(userId);
				if(user.isPresent()) {
					user.get().setEnabled(false);
					userRepo.saveAndFlush(user.get());
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	@Override
	public boolean userEnable(int userId, String username) {
		try {
			User admin = userRepo.findByUsername(username);
			if(admin.getRole().equalsIgnoreCase("admin")) {
				Optional<User> user = userRepo.findById(userId);
				if(user.isPresent()) {
					user.get().setEnabled(true);
					userRepo.saveAndFlush(user.get());
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
