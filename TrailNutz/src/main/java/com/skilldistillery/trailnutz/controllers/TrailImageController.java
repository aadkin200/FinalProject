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

import com.skilldistillery.trailnutz.entities.TrailImage;
import com.skilldistillery.trailnutz.entities.TrailResource;
import com.skilldistillery.trailnutz.services.TrailImageService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4210"})
public class TrailImageController {
	
	@Autowired
	private TrailImageService tiServ;
	
	@PostMapping("trail/{trailId}/trailimg")
	public TrailImage addTrailImage(@RequestBody TrailImage trailImage, int trailId, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {

		try {
			trailImage = tiServ.addTrailImage(trailImage);
			res.setStatus(200);
			return trailImage;
		} catch (Exception e) {
			res.setStatus(400);
			trailImage = null;
		}

		return trailImage;
	}
	
	@PutMapping("trail/{trailId}/trailimg/{trailImgId}")
	public TrailImage removeTraiImage(@RequestBody TrailImage trailImage, int trailImgId, int trailId, Principal principal, HttpServletResponse res) {
		
		try {
			trailImage = tiServ.updateTrailImage(trailImage, trailImgId, trailId);
			res.setStatus(200);
			return trailImage;
}
	catch(Exception e) {
		res.setStatus(400);
		trailImage = null;
	}
	
	return trailImage;
		
	}
	

}
