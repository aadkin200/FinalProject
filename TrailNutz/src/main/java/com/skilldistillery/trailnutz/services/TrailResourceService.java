package com.skilldistillery.trailnutz.services;

import com.skilldistillery.trailnutz.entities.TrailResource;

public interface TrailResourceService  {

	TrailResource addTrailResource(String string, TrailResource trailResource);

	TrailResource updateTrailResource(TrailResource trailResource, String string, int trailResourceId, int trailId);
	
	

}
