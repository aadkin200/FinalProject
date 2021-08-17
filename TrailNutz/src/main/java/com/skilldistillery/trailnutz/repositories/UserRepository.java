package com.skilldistillery.trailnutz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.trailnutz.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
