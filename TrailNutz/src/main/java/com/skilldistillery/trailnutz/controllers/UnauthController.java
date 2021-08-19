package com.skilldistillery.trailnutz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.trailnutz.entities.Trail;
import com.skilldistillery.trailnutz.services.TrailService;

@RestController
@RequestMapping("AllTrails")
@CrossOrigin({"*", "http://localhost:4210"})
public class UnauthController {

	@Autowired
	private TrailService trailSvc;
	
	@GetMapping("trail")
	public List<Trail> index(HttpServletRequest req,
							 HttpServletResponse res) {
		return trailSvc.index();
	}
}
