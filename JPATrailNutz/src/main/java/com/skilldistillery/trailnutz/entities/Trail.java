package com.skilldistillery.trailnutz.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Trail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToMany(mappedBy="favoritedTrails")
	private List<User> users;
	@ManyToOne
	@JoinColumn(name="difficulty_id")
	private Difficulty difficulty;
	@ManyToOne
	@JoinColumn(name="route_type_id")
	private RouteType routeType;
	private String name;
	@Column(name="elevation_change_feet")
	private int elevationChangeFeet;
	@Column(name="trailhead_latitude")
	private String trailheadLatitude;
	@Column(name="trailhead_longitude")
	private String trailheadLongitude;
	private String city;
	private String state;
	private String hazards;
	private String wildlife;
	private String details;
	private double distanceMiles;
	@Column(name="created_at")
	private LocalDateTime createdAt;
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	private boolean enabled;
	private boolean approved;
	private TrailImage trailImages;
	
	
	public Trail() {}
	
	public Trail(int id, List<User> users, Difficulty difficulty, RouteType routeType, String name,
			int elevationChangeFeet, String trailheadLatitude, String trailheadLongitude, String city, String state,
			String hazards, String wildlife, String details, double distanceMiles, LocalDateTime createdAt,
			LocalDateTime updatedAt, boolean enabled, boolean approved) {
		super();
		this.id = id;
		this.users = users;
		this.difficulty = difficulty;
		this.routeType = routeType;
		this.name = name;
		this.elevationChangeFeet = elevationChangeFeet;
		this.trailheadLatitude = trailheadLatitude;
		this.trailheadLongitude = trailheadLongitude;
		this.city = city;
		this.state = state;
		this.hazards = hazards;
		this.wildlife = wildlife;
		this.details = details;
		this.distanceMiles = distanceMiles;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.enabled = enabled;
		this.approved = approved;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	public RouteType getRouteType() {
		return routeType;
	}
	public void setRouteType(RouteType routeType) {
		this.routeType = routeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getElevationChangeFeet() {
		return elevationChangeFeet;
	}
	public void setElevationChangeFeet(int elevationChangeFeet) {
		this.elevationChangeFeet = elevationChangeFeet;
	}
	public String getTrailheadLatitude() {
		return trailheadLatitude;
	}
	public void setTrailheadLatitude(String trailheadLatitude) {
		this.trailheadLatitude = trailheadLatitude;
	}
	public String getTrailheadLongitude() {
		return trailheadLongitude;
	}
	public void setTrailheadLongitude(String trailheadLongitude) {
		this.trailheadLongitude = trailheadLongitude;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHazards() {
		return hazards;
	}
	public void setHazards(String hazards) {
		this.hazards = hazards;
	}
	public String getWildlife() {
		return wildlife;
	}
	public void setWildlife(String wildlife) {
		this.wildlife = wildlife;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public double getDistanceMiles() {
		return distanceMiles;
	}
	public void setDistanceMiles(double distanceMiles) {
		this.distanceMiles = distanceMiles;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trail other = (Trail) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Trail [id=" + id + ", users=" + users + ", name=" + name + ", elevationChangeFeet="
				+ elevationChangeFeet + ", trailheadLatitude=" + trailheadLatitude + ", trailheadLongitude="
				+ trailheadLongitude + ", city=" + city + ", state=" + state + ", hazards=" + hazards + ", wildlife="
				+ wildlife + ", details=" + details + ", distanceMiles=" + distanceMiles + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", enabled=" + enabled + ", approved=" + approved + "]";
	}
	

}
