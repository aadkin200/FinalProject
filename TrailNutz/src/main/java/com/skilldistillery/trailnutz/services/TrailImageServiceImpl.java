package com.skilldistillery.trailnutz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.trailnutz.entities.TrailImage;
import com.skilldistillery.trailnutz.entities.TrailResource;
import com.skilldistillery.trailnutz.repositories.TrailImageRepository;

@Service
public class TrailImageServiceImpl implements TrailImageService {
	
	@Autowired
	private TrailImageRepository tiRepo;

	@Override
	public TrailImage addTrailImage(TrailImage trailImage) {
		
		return tiRepo.save(trailImage);
	}

	@Override
	public TrailImage updateTrailImage(TrailImage trailImage, int trailImgId, int trailId) {
		return tiRepo.saveAndFlush(trailImage);
	}
	

}
