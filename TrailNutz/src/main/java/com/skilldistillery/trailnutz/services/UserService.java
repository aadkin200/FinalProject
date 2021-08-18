package com.skilldistillery.trailnutz.services;

import com.skilldistillery.trailnutz.entities.User;

public interface UserService {
	User userByUsername(String username);
	User updateUser(User user, String username);
	boolean disableUser(int userId, String username);
}
