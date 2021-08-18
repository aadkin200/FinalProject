package com.skilldistillery.trailnutz.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trail_resource")
public class TrailResource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "trail_id")
	private int trailId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "resource_url")
	private String resourceUrl;
	
	
	private String title;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	
	private int enabled;


	
	
	public TrailResource() {
		super();
	}


	public TrailResource(int id, int trailId, int userId, String resourceUrl, String title, LocalDateTime createdAt,
			int enabled) {
		super();
		this.id = id;
		this.trailId = trailId;
		this.userId = userId;
		this.resourceUrl = resourceUrl;
		this.title = title;
		this.createdAt = createdAt;
		this.enabled = enabled;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getTrailId() {
		return trailId;
	}


	public void setTrailId(int trailId) {
		this.trailId = trailId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getResourceUrl() {
		return resourceUrl;
	}


	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public int getEnabled() {
		return enabled;
	}


	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
		TrailResource other = (TrailResource) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "TrailResource [id=" + id + ", trailId=" + trailId + ", userId=" + userId + ", resourceUrl="
				+ resourceUrl + ", title=" + title + ", createdAt=" + createdAt + ", enabled=" + enabled + "]";
	}
	
	
	
	
}
