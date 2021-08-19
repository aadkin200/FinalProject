package com.skilldistillery.trailnutz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.trailnutz.entities.Difficulty;
import com.skilldistillery.trailnutz.repositories.DifficultyRepository;
@Service
public class DifficultyServiceImpl implements DifficultyService {
	
	@Autowired
	private DifficultyRepository repo;

	@Override
	public List<Difficulty> diff() {
		return repo.findAll();
	}

}
