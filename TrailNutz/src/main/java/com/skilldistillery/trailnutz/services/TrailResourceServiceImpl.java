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
	public TrailResource addTrailResource(TrailResource trailResource) {
		
		
		return trRepo.save(trailResource);
	}

	@Override
	public TrailResource updateTrailResource(TrailResource trailResource, int trailResourceId, int trailId) {
		return trRepo.saveAndFlush(trailResource);
	}

	
}
