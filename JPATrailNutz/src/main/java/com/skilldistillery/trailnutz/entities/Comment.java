package com.skilldistillery.trailnutz.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "trail_id")
	private int trailId;
	
	@Column(name = "user_id")
	private int userId;
	
	private String message;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "in_reply_to")
	private int inReplyTo;

	private int enabled;
	
	private String subject;

	
	
	
	public Comment() {
		super();
	}

	public Comment(int id, int trailId, int userId, String message, LocalDateTime createdAt, LocalDateTime updatedAt,
			int inReplyTo, int enabled, String subject) {
		super();
		this.id = id;
		this.trailId = trailId;
		this.userId = userId;
		this.message = message;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.inReplyTo = inReplyTo;
		this.enabled = enabled;
		this.subject = subject;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public int getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(int inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", trailId=" + trailId + ", userId=" + userId + ", message=" + message
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", inReplyTo=" + inReplyTo + ", enabled="
				+ enabled + ", subject=" + subject + "]";
	}
	
	
	
	
}
