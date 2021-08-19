package com.skilldistillery.trailnutz.services;

import java.util.List;

import com.skilldistillery.trailnutz.entities.Comment;

public interface CommentService {

	List<Comment> getCommentByTrailId(Integer trailId);
	
	Comment showComment(int commId);
	
	Comment createComment(Comment comment);
	
	Comment updateComment(int commId, Comment comment);
	
	boolean disableComment(int userId, int commId);
}
