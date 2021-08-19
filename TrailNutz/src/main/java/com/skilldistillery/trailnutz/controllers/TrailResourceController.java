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

import com.skilldistillery.trailnutz.entities.TrailResource;
import com.skilldistillery.trailnutz.services.TrailResourceService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4210" })
public class TrailResourceController {

	@Autowired
	private TrailResourceService trServ;

	@PostMapping("trail/{trailId}/trailresources")
	public TrailResource addTrailResource(@RequestBody TrailResource trailResource, @PathVariable int trailId,
			HttpServletRequest req, HttpServletResponse res, Principal principal) {

		try {
			trailResource = trServ.addTrailResource(trailResource, principal.getName(), trailId);
			res.setStatus(201);
		} catch (Exception e) {
			res.setStatus(400);
			trailResource = null;
		}

		return trailResource;
	}

	@DeleteMapping("trail/{trailId}/trailresources/{trailResourceId}")
	public boolean removeTraiResource(@PathVariable int trailId, @PathVariable int trailResourceId,  Principal principal, HttpServletResponse res) {

		boolean isDisabled;
		try {
			isDisabled = trServ.disableTrailResource(trailResourceId, trailId, principal.getName());
			res.setStatus(204);
			return isDisabled;
		} catch (Exception e) {
			res.setStatus(404);
			return false;
		}
	}
}
