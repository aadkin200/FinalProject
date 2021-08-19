package com.skilldistillery.trailnutz.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.trailnutz.entities.TrailResource;
import com.skilldistillery.trailnutz.repositories.TrailResourceRepository;

@Service
public class TrailResourceServiceImpl implements TrailResourceService {
	
	@Autowired
	private TrailResourceRepository trRepo;

	@Override
	public TrailResource addTrailResource(String string, TrailResource trailResource) {
		
		
		return trRepo.save(trailResource);
	}

	@Override
	public TrailResource updateTrailResource(TrailResource trailResource, String string, int trailResourceId, int trailId) {
//		TrailResource managed = trRepo.findByUsernameAndTrailId(string, trailId);
//		if(managed != null) {
//			managed.setResourceUrl(trailResource.getResourceUrl());
//		}
		
		return null;
		
		
	}
	
	
	
}
