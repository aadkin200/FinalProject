package com.skilldistillery.trailnutz.services;

import java.util.List;

import com.skilldistillery.trailnutz.entities.User;

public interface UserService {
	User userByUsername(String username);
	User updateUser(User user, String username);
	boolean disableUser(int userId, String username);
	boolean userEnable(int userId, String username);
	List<User> getAllUsers();
	long hasFavoriteTrail(String username, int trailId);
	User updateFavoriteTrails(int trailId, String username);
	
	}
