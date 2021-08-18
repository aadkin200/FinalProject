package com.skilldistillery.trailnutz.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
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

	public LocalDateTime getCreateAt() {
		return createdAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createdAt = createAt;
	}

}
