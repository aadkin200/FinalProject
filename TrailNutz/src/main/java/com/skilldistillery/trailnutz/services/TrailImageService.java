package com.skilldistillery.trailnutz.services;

import com.skilldistillery.trailnutz.entities.TrailImage;

public interface TrailImageService {

	TrailImage addTrailImage(TrailImage trailImage);

	TrailImage updateTrailImage(TrailImage trailImage, int trailImgId, int trailId);

}
