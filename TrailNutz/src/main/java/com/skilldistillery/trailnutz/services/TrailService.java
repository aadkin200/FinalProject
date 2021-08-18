package com.skilldistillery.trailnutz.services;

import java.util.List;

import com.skilldistillery.trailnutz.entities.Trail;

public interface TrailService {
	public List<Trail> index(String username);
	public Trail show(String username, int trailId);
	public Trail create(String username, Trail trail);
	public Trail update(String username, Trail trail, int trailId);
	public boolean destroy(String username, int trailId);

}
