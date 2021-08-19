package com.skilldistillery.trailnutz.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.trailnutz.entities.TrailResource;
import com.skilldistillery.trailnutz.services.TrailResourceService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class TrailResourceController {

	@Autowired
	private TrailResourceService trServ;

	@PostMapping("trail/{trailId}/trailresources")
	public TrailResource addTrailResource(@RequestBody TrailResource trailResource, int trailId, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {

		try {
			trailResource = trServ.addTrailResource(trailResource);
			res.setStatus(200);
			return trailResource;
		} catch (Exception e) {
			res.setStatus(400);
			trailResource = null;
		}

		return trailResource;
	}
	
	@PutMapping("trail/{trailId}/trailresources/{trailResourceId}")
	public TrailResource removeTraiResource(@RequestBody TrailResource trailResource, int trailResourceId, int trailId, Principal principal, HttpServletResponse res) {
		
		try {
			 trailResource = trServ.updateTrailResource(trailResource, trailResourceId, trailId);
			res.setStatus(200);
			return trailResource;
}
	catch(Exception e) {
		res.setStatus(400);
		trailResource = null;
	}
	
	return trailResource;
		
	}
	

}
