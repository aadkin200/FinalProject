package com.skilldistillery.trailnutz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.trailnutz.entities.TrailImage;

public interface TrailImageRepository extends JpaRepository<TrailImage, Integer> {



	TrailImage getById(int trailImgId);

}
