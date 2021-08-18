package com.skilldistillery.trailnutz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.trailnutz.entities.RouteType;
import com.skilldistillery.trailnutz.repositories.RouteTypeRepository;

@Service
public class RouteTypeServiceImpl implements RouteTypeService {
	
	@Autowired
	private RouteTypeRepository repo;

	@Override
	public List<RouteType> routes() {
		return repo.findAll();
	}

	
	
}
