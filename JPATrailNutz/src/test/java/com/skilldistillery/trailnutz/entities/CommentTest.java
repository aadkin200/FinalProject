package com.skilldistillery.trailnutz.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {
	
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPATrailNutz");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@Test
	@DisplayName("comment entity test")
	void test() {
		assertNotNull(comment);
		assertEquals("Hello", comment.getMessage());
		
		LocalDateTime createDate =  LocalDateTime.of(2020, 01, 01, 10, 10, 00);
		assertEquals(createDate, comment.getCreatedAt());
		
		assertEquals(1, comment.getReplies().size());
	}
	
	@Test
	@DisplayName("comment to user test")
	void test1() {
		
		assertNotNull(comment.getUser());
		assertEquals(1, comment.getUser().getId());
		assertEquals("Gerry", comment.getUser().getFirstName());
		
	}
	
	@Test
	@DisplayName("comment to trail test")
	void test2() {
		
		assertNotNull(comment.getTrail());
		assertEquals(1, comment.getTrail().getId());
		assertEquals("Racoons", comment.getTrail().getWildlife());
		
	}

}
