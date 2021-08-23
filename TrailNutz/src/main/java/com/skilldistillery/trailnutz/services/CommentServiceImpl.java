package com.skilldistillery.trailnutz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.trailnutz.entities.Comment;
import com.skilldistillery.trailnutz.entities.Trail;
import com.skilldistillery.trailnutz.entities.User;
import com.skilldistillery.trailnutz.repositories.CommentRepository;
import com.skilldistillery.trailnutz.repositories.TrailRepository;
import com.skilldistillery.trailnutz.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TrailRepository trailRepo;

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
	public Comment createComment(int trailId, Comment comment, String username) {
		User managedUser = userRepo.findByUsername(username);
		Trail trail = trailRepo.findById(trailId).get();
		comment.setTrail(trail);
		comment.setUser(managedUser);
		commRepo.saveAndFlush(comment);
		
		return comment;
	}
	
	
	@Override
	public Comment updateComment(int trailId, int commId, Comment comment, String username) {
		User managedUser = userRepo.findByUsername(username);
		Trail trail = trailRepo.findById(trailId).get();
		comment.setTrail(trail);
		comment.setUser(managedUser);
		comment.setId(commId);
		commRepo.saveAndFlush(comment);
		
		return comment;
	}
	
	
	@Override
	public boolean disableComment(int userId, int commId, String username) {
		
			Optional<Comment> commentOpt = commRepo.findById(commId);
			if(commentOpt.isPresent()) {
				
				Comment comment = commentOpt.get();
				User managedUser = userRepo.findByUsername(username);
				
				if(managedUser.getRole().equals("admin") || managedUser.getId() == userId) {
					
					comment.setEnabled(false);
					commRepo.saveAndFlush(comment);
					return true;
				}
		}
		return false;
	}

	@Override
	public List<Comment> findByParentComment_Id(int commentId) {
		return commRepo.findByParentComment_Id(commentId);
	}
	
}
