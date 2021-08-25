package com.skilldistillery.trailnutz.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.trailnutz.entities.Amenity;
import com.skilldistillery.trailnutz.entities.Trail;
import com.skilldistillery.trailnutz.repositories.AmenityRepository;
import com.skilldistillery.trailnutz.services.AmenityService;
import com.skilldistillery.trailnutz.services.CommentService;
import com.skilldistillery.trailnutz.services.TrailService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4210"})
public class TrailController {
	@Autowired
	private TrailService trailSvc;
	@Autowired
	private CommentService commentSvc;
	@Autowired
	private AmenityRepository amenRepo;
	@Autowired
	private AmenityService amenSvc;
	
	@GetMapping("trail")
	public List<Trail> index(HttpServletRequest req,
							 HttpServletResponse res,
							 Principal principal) {
		return trailSvc.index();
	}
	
	@GetMapping("trail/{trailId}")
	public Trail show(@PathVariable int trailId,
					  HttpServletRequest req,
					  HttpServletResponse res,
					  Principal principal) {
		return trailSvc.show(trailId);
	}
	
	@PostMapping("trail")
	public Trail create(@RequestBody Trail trail, 
						HttpServletRequest req, 
						HttpServletResponse res, 
						Principal principal) {
		return trailSvc.create(principal.getName(), trail);
	}
	
	@PutMapping("trail")
	public Trail update(@RequestBody Trail trail,
						HttpServletRequest req, 
						HttpServletResponse res, 
						Principal principal) {
		return trailSvc.update(principal.getName(), trail, trail.getId());
	}
	
	@PutMapping("trail/{trailId}")
	public boolean destroy(@PathVariable int trailId,
						HttpServletRequest req,
						HttpServletResponse res,
						Principal principal) {
		return trailSvc.update(principal.getName(), trailId);
	}
	
	@PostMapping("trail/{trailId}/amenity")
	public List<Amenity> addAmenities(@PathVariable int trailId,
									  @RequestBody List<Amenity> amenities,
									  HttpServletRequest req,
									  HttpServletResponse res,
									  Principal principal) {
		Trail managed = trailSvc.show(principal.getName(), trailId);
		for (Amenity amenity : amenities) {
			amenity.getTrails().add(managed);
			amenRepo.saveAndFlush(amenity);
		}
		
		managed.setAmenities(amenities);
		return amenities;
	}
}
