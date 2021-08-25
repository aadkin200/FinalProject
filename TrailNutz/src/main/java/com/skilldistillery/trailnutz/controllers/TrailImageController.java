package com.skilldistillery.trailnutz.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.trailnutz.entities.TrailImage;
import com.skilldistillery.trailnutz.entities.TrailResource;
import com.skilldistillery.trailnutz.repositories.TrailImageRepository;
import com.skilldistillery.trailnutz.services.TrailImageService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class TrailImageController {

	@Autowired
	private TrailImageService tiServ;

	@PostMapping("trail/{trailId}/trailimg")
	public TrailImage addTrailImage(@RequestBody TrailImage trailImage, @PathVariable int trailId,
		HttpServletRequest req, HttpServletResponse res, Principal principal) {
		System.out.println(trailImage);
		TrailImage trailImg = tiServ.addTrailImage(principal.getName(), trailImage, trailId);
		if (trailImage == null) {

			res.setStatus(400);

		} else {
			res.setStatus(201);
		}
		return trailImg;
	}

	@DeleteMapping("trail/{trailId}/trailimg/{trailImgId}")
	public void removeTraiImage(@PathVariable int trailImgId, @PathVariable int trailId, Principal principal,
			HttpServletResponse res) {
		boolean isDisabled = tiServ.updateTrailImage(trailImgId, trailId);
		if (isDisabled) {
			res.setStatus(204);
		} else {
			res.setStatus(400);
		}

	}

}
