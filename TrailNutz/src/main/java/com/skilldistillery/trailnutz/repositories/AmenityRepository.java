package com.skilldistillery.trailnutz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.trailnutz.entities.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Integer> {

}
