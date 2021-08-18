package com.skilldistillery.trailnutz.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private String message;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "in_reply_to")
	private Integer inReplyTo;

	private int enabled;
	
	private String subject;
	
	@ManyToOne
	@JoinColumn(name="trail_id")
	private Trail trail;

	
	
	
	public Comment() {
		super();
	}

	public Comment(int id, User user, Trail trail, String message, LocalDateTime createdAt, LocalDateTime updatedAt,
			int inReplyTo, int enabled, String subject) {
		super();
		this.id = id;
		this.trail = trail;
		this.user = user;
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

	public Trail getTrail() {
		return trail;
	}

	public void setTrail(Trail trail) {
		this.trail = trail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Integer getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(Integer inReplyTo) {
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
		return "Comment [id=" + id + ", trail=" + trail + ", user=" + user + ", message=" + message
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", inReplyTo=" + inReplyTo + ", enabled="
				+ enabled + ", subject=" + subject + "]";
	}
	
	
	
	
}
