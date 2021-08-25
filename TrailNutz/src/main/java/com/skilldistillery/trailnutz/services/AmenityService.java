package com.skilldistillery.trailnutz.services;

import java.util.List;

import com.skilldistillery.trailnutz.entities.Amenity;

public interface AmenityService {
	
	List<Amenity> amenities();
	Amenity addAmenity(String username, Amenity amenity, int trailId);

}
