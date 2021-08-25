package com.skilldistillery.trailnutz.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.trailnutz.entities.Comment;
import com.skilldistillery.trailnutz.entities.User;
import com.skilldistillery.trailnutz.services.CommentService;

@CrossOrigin({"*", "http://localhost:4210"})
@RequestMapping("api")
@RestController
public class CommentController {
	
	@Autowired
	private CommentService commSrv;
	
//  Get Comment of Comments
	@GetMapping("trail/comment/{commentId}")
	public List<Comment> findByParentCommentId(@PathVariable int commentId, HttpServletResponse res) {
		return commSrv.findByParentComment_Id(commentId);
	}
	
	
//	POST api/trail/{trailId}/comment 
	@PostMapping("trail/{trailId}/comment")
	public Comment createComment(@PathVariable Integer trailId, @RequestBody Comment comment, HttpServletRequest req,
			HttpServletResponse res, Principal principal){
		String username = principal.getName();
		//prevent lazy load on parent comment
		System.out.println(comment);
		Comment newComment = commSrv.createComment(trailId, comment, username);
		if(newComment == null) {
			res.setStatus(400);
		}
		return newComment;
	}

	
//	PUT api/trail/{trailId}/comment
	@PutMapping("trail/{trailId}/comment")
	public Comment updateComment(@PathVariable Integer trailId, @RequestBody Comment comment,
			HttpServletResponse res, Principal principal) {
		String username = principal.getName();
		Comment updatedComment = commSrv.updateComment(trailId, comment.getId(), comment, username);
		if(updatedComment == null) {
			res.setStatus(400);
		}
		return updatedComment;
	}
	
	
//	DEL api/trail/{trailId}/comment/{commentId}
	@DeleteMapping("trail/{trailId}/comment/{commentId}")
	public void deleteComment(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer trailId, @PathVariable Integer commentId, Principal principal){
		String username = principal.getName();
		Comment comment = commSrv.showComment(commentId);
		User user = comment.getUser();
		
		Boolean deleted = commSrv.disableComment(user.getId(), commentId, username);
		if(deleted) {
			res.setStatus(200);
		}
		else {
			res.setStatus(400);
		}
	}

}
