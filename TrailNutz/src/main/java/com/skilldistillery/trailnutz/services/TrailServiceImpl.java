package com.skilldistillery.trailnutz.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.trailnutz.entities.Trail;
import com.skilldistillery.trailnutz.entities.User;
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
		//Sysouts used to prevent lazy loading issues on update
		System.out.println(trail.getTrailImages());
		System.out.println(trail.getComments());
		Trail managed = trailRepo.findByUser_UsernameAndId(username, trailId);
		System.out.println(managed.getTrailImages());
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
		trail.getComments().removeIf(comment -> comment.getEnabled() == false);
		trail.getComments().forEach(comment -> comment.getReplies().removeIf(reply -> reply.getEnabled() == false));
		trail.getTrailResource().removeIf(resource -> resource.getEnabled() == false);
		return trail;
	}

	@Override
	public boolean update(String username, int trailId) {
		User user = userRepo.findByUsername(username);
		Trail managed = trailRepo.findById(trailId).get();
		if(user.getRole().equalsIgnoreCase("admin") || managed.getUser().getUsername().equalsIgnoreCase(username)) {
			managed.setEnabled(false);
			trailRepo.saveAndFlush(managed);
		}
		
		return !managed.isEnabled();
	}


}
