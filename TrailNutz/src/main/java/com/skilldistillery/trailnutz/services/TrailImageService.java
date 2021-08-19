package com.skilldistillery.trailnutz.services;


import com.skilldistillery.trailnutz.entities.TrailImage;

public interface TrailImageService {

	TrailImage addTrailImage(String username, TrailImage trailImage, int trailId);

	boolean updateTrailImage( int trailImgId, int trailId);

}
