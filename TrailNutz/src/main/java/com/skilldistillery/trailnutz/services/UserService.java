package com.skilldistillery.trailnutz.services;

import com.skilldistillery.trailnutz.entities.User;

public interface UserService {
	User userByUsername(String username);
}
