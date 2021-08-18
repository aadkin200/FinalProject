package com.skilldistillery.trailnutz.services;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.skilldistillery.trailnutz.entities.User;
import com.skilldistillery.trailnutz.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User userByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User updateUser(User user, String username) {
		User managedUser = userRepo.findByUsername(username);
		if(user.getId() == managedUser.getId()) {
			managedUser.setFavoriteTrailFood(user.getFavoriteTrailFood());
			managedUser.setImageUrl(user.getImageUrl());
			managedUser.setFirstName(user.getFirstName());
			managedUser.setLastName(user.getLastName());
			userRepo.saveAndFlush(managedUser);
		}
		return managedUser;
	}

	@Override
	public boolean disableUser(int userId, String username) {
		User admin = userRepo.findByUsername(username);
		if(admin.getRole().equals("admin")) {
			Optional<User> user = userRepo.findById(userId);
			if(user.isPresent()) {
				user.get().setEnabled(false);
				userRepo.saveAndFlush(user.get());
				return true;
			}
		}
		return false;
	}
}
