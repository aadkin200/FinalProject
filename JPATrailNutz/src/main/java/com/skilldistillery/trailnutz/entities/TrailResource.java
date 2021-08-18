package com.skilldistillery.trailnutz.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trail_resource")
public class TrailResource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "resource_url")
	private String resourceUrl;
	
	
	private String title;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	
	private int enabled;

	@ManyToOne
	@JoinColumn(name="trail_id")
	private Trail trail;
	
	
	public TrailResource() {
		super();
	}


	public TrailResource(int id, Trail trail, User user, String resourceUrl, String title, LocalDateTime createdAt,
			int enabled) {
		super();
		this.id = id;
		this.trail = trail;
		this.user = user;
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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
	


	public Trail getTrail() {
		return trail;
	}


	public void setTrail(Trail trail) {
		this.trail = trail;
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
		return "TrailResource [id=" + id + ", trail=" + trail + ", user=" + user + ", resourceUrl="
				+ resourceUrl + ", title=" + title + ", createdAt=" + createdAt + ", enabled=" + enabled + "]";
	}
	
	
	
	
	
	
}
