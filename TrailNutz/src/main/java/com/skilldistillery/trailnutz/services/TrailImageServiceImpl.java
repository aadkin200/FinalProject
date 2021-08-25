package com.skilldistillery.trailnutz.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.skilldistillery.trailnutz.entities.Trail;
import com.skilldistillery.trailnutz.entities.TrailImage;
import com.skilldistillery.trailnutz.entities.TrailResource;
import com.skilldistillery.trailnutz.entities.User;
import com.skilldistillery.trailnutz.repositories.TrailImageRepository;
import com.skilldistillery.trailnutz.repositories.TrailRepository;
import com.skilldistillery.trailnutz.repositories.UserRepository;

@Service
public class TrailImageServiceImpl implements TrailImageService {
	
	@Autowired
	private TrailImageRepository tiRepo;
	
	@Autowired
	private TrailRepository tRepo;
	
	@Autowired
	private UserRepository uRepo;
	

	@Override
	public TrailImage addTrailImage(String username, TrailImage trailImage, int trailId ) {
		User user = uRepo.findByUsername(username);
		
		try {
			Trail trail = tRepo.findById(trailId).get();
			System.out.println(trail.getTrailImages());
			trailImage.setTrail(trail);
			trailImage.setUser(user);
		} catch (Exception e) {
			trailImage = null;
		}
		System.out.println(trailImage);
		return tiRepo.saveAndFlush(trailImage);
	}

	@Override
	public boolean updateTrailImage( int trailImgId, int trailId) {
		
		TrailImage managed = tiRepo.getById( trailImgId);
		if(managed!=null) {
			tiRepo.delete(managed);
			return true;
		}
		
		return false;
	}
	
	
}
