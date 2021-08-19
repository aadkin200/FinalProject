package com.skilldistillery.trailnutz.services;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.trailnutz.entities.Trail;
import com.skilldistillery.trailnutz.entities.TrailResource;
import com.skilldistillery.trailnutz.entities.User;
import com.skilldistillery.trailnutz.repositories.TrailRepository;
import com.skilldistillery.trailnutz.repositories.TrailResourceRepository;
import com.skilldistillery.trailnutz.repositories.UserRepository;

@Service
public class TrailResourceServiceImpl implements TrailResourceService {
	
	@Autowired
	private TrailResourceRepository trRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TrailRepository trailRepo;

	@Override
	public TrailResource addTrailResource(TrailResource trailResource, String username, int trailId) {
		User user = userRepo.findByUsername(username);
		Optional<Trail> trail = trailRepo.findById(trailId);
		if(trail.isPresent()) {
			trailResource.setTrail(trail.get());
		}
		trailResource.setUser(user);
		trailResource.setEnabled(true);
		trRepo.saveAndFlush(trailResource);
		return trailResource;
		}
	


	@Override
	public TrailResource updateTrailResource(TrailResource trailResource, String string, int trailResourceId, int trailId) {

		return null;
		
		
	}


	@Override
	public boolean disableTrailResource(int trailResourceId, int trailId, String username) {
		TrailResource trailResource = trRepo.findById(trailResourceId).get();
		User user = userRepo.findByUsername(username);
		Trail trail = trailRepo.findById(trailId).get();
		boolean hasResources = trail.getTrailResource() != null;
		if(hasResources && trailResource.getUser().getId() == user.getId() || hasResources && user.getRole().equalsIgnoreCase("ADMIN")) {
			if(trail.getTrailResource().contains(trailResource)) {
				for(TrailResource tr : trail.getTrailResource()) {
					if(tr.getId() == trailResourceId) {
						tr.setEnabled(false);
						trRepo.saveAndFlush(tr);
					}
				}
			}
		}
		return false;
	}

}
