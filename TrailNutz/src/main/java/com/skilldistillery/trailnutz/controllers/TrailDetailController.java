package com.skilldistillery.trailnutz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.trailnutz.entities.Amenity;
import com.skilldistillery.trailnutz.entities.Difficulty;
import com.skilldistillery.trailnutz.entities.RouteType;
import com.skilldistillery.trailnutz.services.AmenityService;
import com.skilldistillery.trailnutz.services.DifficultyService;
import com.skilldistillery.trailnutz.services.RouteTypeService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class TrailDetailController {
	
	@Autowired
	DifficultyService difficultyService;
	@Autowired
	RouteTypeService routeService;
	@Autowired
	AmenityService amenityService;
	
	@GetMapping("traildetails/difficulty")
	public List<Difficulty> difficultyIndex(){
		return difficultyService.diff();
	}
	
	@GetMapping("traildetails/routetype")
	public List<RouteType> routeTypeIndex(){
		return routeService.routes();
	}
	
	@GetMapping("traildetails/amenity")
	public List<Amenity> amenityIndex(){
		return amenityService.amenities();
	}
	

}
