package com.skilldistillery.trailnutz.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@JsonIgnoreProperties("replies")
	@ManyToOne
	@JoinColumn(name = "in_reply_to")
	private Comment parentComment;
	
	
	@OneToMany(mappedBy="parentComment")
	List<Comment> replies;
	
	private boolean enabled;
	
	private String subject;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="trail_id")
	private Trail trail;

	
	
	
	public Comment() {
		super();
	}

	public Comment(int id, User user, String message, LocalDateTime createdAt, LocalDateTime updatedAt,
			Comment parentComment, List<Comment> replies, boolean enabled, String subject, Trail trail) {
		super();
		this.id = id;
		this.user = user;
		this.message = message;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.parentComment = parentComment;
		this.replies = replies;
		this.enabled = enabled;
		this.subject = subject;
		this.trail = trail;

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

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
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



	public List<Comment> getReplies() {
		return replies;
	}



	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}



	public Comment getParentComment() {
		return parentComment;
	}



	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", user=" + user + ", message=" + message + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", parentComment=" + parentComment + ", replies=" + replies
				+ ", enabled=" + enabled + ", subject=" + subject + ", trail=" + trail + "]";
	}
	
	
}
