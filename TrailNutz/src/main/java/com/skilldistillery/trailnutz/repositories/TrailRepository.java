package com.skilldistillery.trailnutz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.trailnutz.entities.Trail;

public interface TrailRepository extends JpaRepository<Trail, Integer>{
	List<Trail> findByUser_Username(String username);
	Trail findByUser_UsernameAndId(String username, int id);

}
