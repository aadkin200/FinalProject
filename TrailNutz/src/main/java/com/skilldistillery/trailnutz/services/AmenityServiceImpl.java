package com.skilldistillery.trailnutz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.trailnutz.entities.Amenity;
import com.skilldistillery.trailnutz.repositories.AmenityRepository;

@Service
public class AmenityServiceImpl implements AmenityService {
	
	@Autowired
	private AmenityRepository repo;

	@Override
	public List<Amenity> amenities() {
		return repo.findAll();
	}
	
	

}
