package com.skilldistillery.trailnutz.services;

import com.skilldistillery.trailnutz.entities.TrailResource;

public interface TrailResourceService  {

	TrailResource addTrailResource(TrailResource trailResource, String username, int trailId);

	TrailResource updateTrailResource(TrailResource trailResource, int trailResourceId, int trailId);
	
	boolean disableTrailResource(int trailResourceId, int trailId, String username);
	
	

}
