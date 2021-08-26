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
		Amenity managedAmenity = null;
		try {
			managedAmenity = repo.findById(amenity.getId()).get();
			Trail trail = tRepo.findById(trailId).get();
			managedAmenity.getTrails().add(trail);
			repo.saveAndFlush(managedAmenity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return managedAmenity;
	}

	@Override
	public List<Amenity> updateAmenities(String username, List<Amenity> amenities, int trailId) {
		Trail trail = tRepo.findByUser_UsernameAndId(username, trailId);
		List<Amenity> managedAmenities= repo.findAll();
			
			
			for(Amenity am: managedAmenities) {
			   am.getTrails().remove(trail);
			   repo.saveAndFlush(am);
			}
			
			if(amenities.size() > 0) {
				for (Amenity amenity : amenities) {
					Amenity managedAmenity = repo.findById(amenity.getId()).get();
					if(!managedAmenity.getTrails().contains(trail)) {
						managedAmenity.getTrails().add(trail);	
					}
					
					repo.saveAndFlush(managedAmenity);
				}
			}

		return amenities;
	}

}
