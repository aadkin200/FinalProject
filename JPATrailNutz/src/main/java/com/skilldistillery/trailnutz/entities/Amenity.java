package com.skilldistillery.trailnutz.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Amenity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String name;
	
	private String description;
	
	
	@Column(name="image_url")
	private String imageUrl;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="trail_has_amenity",
			   joinColumns=@JoinColumn(name="amenity_id"), 
			   inverseJoinColumns=@JoinColumn(name="trail_id"))
	private List<Trail> trails;

	public Amenity() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	

	public List<Trail> getTrails() {
		return trails;
	}


	public void setTrails(List<Trail> trails) {
		this.trails = trails;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Amenity [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append("]");
		return builder.toString();
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
		Amenity other = (Amenity) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
