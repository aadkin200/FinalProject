package com.skilldistillery.trailnutz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.trailnutz.entities.Comment;
import com.skilldistillery.trailnutz.entities.Trail;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByTrail_Id(Integer trailId);
	List<Comment> findByParentComment_Id(int commentId);
}
