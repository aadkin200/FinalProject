package com.skilldistillery.trailnutz.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	@DisplayName("user entity test")
	void test() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}
	
	@Test
	@DisplayName("testing LDT user entity")
	void test1() {
		assertNotNull(user);
		assertEquals(2020, user.getCreateAt().getYear());
		System.out.println(user);
	}
	
	@Test
	@DisplayName("User Favorite Trail Join Table")
	void test2() {
		assertNotNull(user.getFavoriteTrails());
		assertTrue(user.getFavoriteTrails().size() > 0);
	}
	
	@Test
	@DisplayName("User to trail mapping")
	void test3() {
		assertNotNull(user.getTrails());
		assertTrue(user.getTrails().size() > 0);
	}
	
	@Test
	@DisplayName("user to trail_resource")
	void test4() {
		assertNotNull(user.getTrailResources());
		assertTrue(user.getTrailResources().size() > 0);
	}
	
	@Test
	@DisplayName("user to comment")
	void test5() {
		assertNotNull(user.getComments());
		assertTrue(user.getComments().size() > 0);
	}

}
