package com.skilldistillery.trailnutz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.trailnutz.entities.Amenity;
import com.skilldistillery.trailnutz.entities.Trail;
import com.skilldistillery.trailnutz.entities.User;
import com.skilldistillery.trailnutz.repositories.AmenityRepository;
import com.skilldistillery.trailnutz.repositories.TrailRepository;
import com.skilldistillery.trailnutz.repositories.UserRepository;

@Service
public class AmenityServiceImpl implements AmenityService {
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private TrailRepository tRepo;
	
	@Autowired
	private AmenityRepository repo;
	
	@Autowired
	private TrailService trailSvc;

	@Override
	public List<Amenity> amenities() {
		return repo.findAll();
	}

	@Override
	public Amenity addAmenity(String username, Amenity amenity, int trailId) {
		User user = uRepo.findByUsername(username);
		
	try {
		Trail trail = tRepo.findById(trailId).get();
		System.out.println(trail.getAmenities());
		amenity.addTrail(trail);
	} catch (Exception e) {
		amenity = null;
	}
	System.out.println(amenity);
	Amenity newAmenity = repo.saveAndFlush(amenity);
	Trail managed = tRepo.findById(trailId).get();
	managed.addAmenity(newAmenity);
	return newAmenity;
	}
	
	

}
