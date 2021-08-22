package com.skilldistillery.trailnutz.services;

import java.util.List;

import com.skilldistillery.trailnutz.entities.Comment;

public interface CommentService {

	List<Comment> getCommentByTrailId(Integer trailId);
	
	Comment showComment(int commId);
	
	Comment createComment(int trailId, Comment comment, String username);
	
	Comment updateComment(int trailId, int commId, Comment comment, String username);
	
	List<Comment> findByParentComment_Id(int commentId);
	
	boolean disableComment(int userId, int commId, String username);
}
