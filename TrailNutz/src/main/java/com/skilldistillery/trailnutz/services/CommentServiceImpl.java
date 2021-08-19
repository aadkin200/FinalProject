package com.skilldistillery.trailnutz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.trailnutz.entities.Comment;
import com.skilldistillery.trailnutz.entities.User;
import com.skilldistillery.trailnutz.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commRepo;

	public List<Comment> getCommentByTrailId(Integer trailId) {
		return commRepo.findByTrail_Id(trailId);
	}
	
	@Override
	public Comment showComment(int commId) {
		
		Comment comment = null;
		Optional<Comment> commOpt = commRepo.findById(commId);
		
		if (commOpt.isPresent()) {
			comment = commOpt.get();
		}
		return comment;
	}
	
	
	@Override
	public Comment createComment(Comment comment) {
		
		return commRepo.saveAndFlush(comment);
	}
	
	
	@Override
	public Comment updateComment(int commId, Comment comment) {
		
		comment.setId(commId);
		return commRepo.saveAndFlush(comment);
	}
	
	
	@Override
	public boolean disableComment(int userId, int commId) {
		
			Optional<Comment> commentOpt = commRepo.findById(commId);
			if(commentOpt.isPresent()) {
				
				Comment comment = commentOpt.get();
				User user = comment.getUser();
				
				if(user.getRole().equals("admin") || user.getId() == userId) {
					
					comment.setEnabled(false);
					commRepo.saveAndFlush(comment);
					return true;
				}
		}
		return false;
	}
	
}
