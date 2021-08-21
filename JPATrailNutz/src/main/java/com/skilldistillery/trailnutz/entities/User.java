package com.skilldistillery.trailnutz.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String username;
	private String role;
	private String password;
	private boolean enabled;
	@Column(name="favorite_trail_food")
	private String favoriteTrailFood;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="image_url")
	private String imageUrl;
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	@JsonIgnore
	@ManyToMany
    @JoinTable(
            name = "user_has_trail", 
            joinColumns = @JoinColumn(name = "user_id"), 
            inverseJoinColumns = @JoinColumn(name = "trail_id")
        )
	List<Trail> favoriteTrails;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<TrailImage> trailImages;
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<TrailResource> trailResources;
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Comment> comments;
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Trail> trails;
	
	//TODO: Other user fields
	public User() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", role=" + role + ", password=" + password + ", enabled="
				+ enabled + "]";
	}

	public String getFavoriteTrailFood() {
		return favoriteTrailFood;
	}

	public void setFavoriteTrailFood(String favoriteTrailFood) {
		this.favoriteTrailFood = favoriteTrailFood;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Trail> getFavoriteTrails() {
		return favoriteTrails;
	}

	public void setFavoriteTrails(List<Trail> favoriteTrails) {
		this.favoriteTrails = favoriteTrails;
	}

	public List<TrailImage> getTrailImages() {
		return trailImages;
	}

	public void setTrailImages(List<TrailImage> trailImages) {
		this.trailImages = trailImages;
	}

	public List<TrailResource> getTrailResources() {
		return trailResources;
	}

	public void setTrailResources(List<TrailResource> trailResources) {
		this.trailResources = trailResources;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Trail> getTrails() {
		return trails;
	}

	public void setTrails(List<Trail> trails) {
		this.trails = trails;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
