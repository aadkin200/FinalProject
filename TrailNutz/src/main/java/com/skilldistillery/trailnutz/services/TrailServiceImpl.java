package com.skilldistillery.trailnutz.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.trailnutz.entities.Trail;
import com.skilldistillery.trailnutz.repositories.TrailRepository;
import com.skilldistillery.trailnutz.repositories.UserRepository;

@Service
public class TrailServiceImpl implements TrailService{
	
	@Autowired
	TrailRepository trailRepo;
	@Autowired
	UserRepository userRepo;

	@Override
	public List<Trail> index(String username) {
		return trailRepo.findByUser_Username(username);
	}
	
	@Override
	public List<Trail> index() {
		return trailRepo.findAll();
	}

	@Override
	public Trail show(String username, int trailId) {
		return trailRepo.findByUser_UsernameAndId(username, trailId);
	}

	@Override
	public Trail create(String username, Trail trail) {
		trail.setUser(userRepo.findByUsername(username));
		return trailRepo.saveAndFlush(trail);
	}

	@Override
	public Trail update(String username, Trail trail, int trailId) {
		Trail managed = trailRepo.findByUser_UsernameAndId(username, trailId);
		if(managed != null) {
			managed.setName(trail.getName());
			managed.setElevationChangeFeet(trail.getElevationChangeFeet());
			managed.setTrailheadLatitude(trail.getTrailheadLatitude());
			managed.setTrailheadLongitude(trail.getTrailheadLongitude());
			managed.setCity(trail.getCity());
			managed.setState(trail.getState());
			managed.setHazards(trail.getHazards());
			managed.setWildlife(trail.getWildlife());
			managed.setDetails(trail.getDetails());
			managed.setDistanceMiles(trail.getDistanceMiles());
			managed.setRouteType(trail.getRouteType());
			managed.setDifficulty(trail.getDifficulty());
			managed.setCreatedAt(trail.getCreatedAt());
			managed.setUpdatedAt(LocalDateTime.now());
			managed.setEnabled(trail.isEnabled());
			managed.setApproved(trail.isApproved());
			return trailRepo.saveAndFlush(managed);
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int trailId) {
		Trail managed = trailRepo.findByUser_UsernameAndId(username, trailId);
		if(managed != null) {
			trailRepo.delete(managed);
			return true;
		}
		return false;
	}

	@Override
	public Trail show(int trailId) {
		Optional<Trail> trailOp = trailRepo.findById(trailId);
		Trail trail = trailOp.get();
		return trail;
	}


}
