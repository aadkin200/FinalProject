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

import com.skilldistillery.trailnutz.entities.Comment;
import com.skilldistillery.trailnutz.entities.Trail;
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
	
	@GetMapping("trail")
	public List<Trail> index(HttpServletRequest req,
							 HttpServletResponse res,
							 Principal principal) {
		return trailSvc.index();
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
						@PathVariable int trailId,
						HttpServletRequest req, 
						HttpServletResponse res, 
						Principal principal) {
		return trailSvc.update(principal.getName(), trail, trailId);
	}
	
	@PutMapping("trail/{trailId}")
	public Trail destroy(@PathVariable int trailId,
						HttpServletRequest req,
						HttpServletResponse res,
						Principal principal) {
		Trail managed = trailSvc.show(principal.getName(), trailId);
		managed.setEnabled(false);
		return trailSvc.update(principal.getName(), managed, trailId);
	}
	
	

}
