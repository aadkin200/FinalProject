package com.skilldistillery.trailnutz.services;

import com.skilldistillery.trailnutz.entities.TrailResource;

public interface TrailResourceService  {

	TrailResource addTrailResource(TrailResource trailResource);

	TrailResource updateTrailResource(TrailResource trailResource, int trailResourceId, int trailId);
	
	

}
