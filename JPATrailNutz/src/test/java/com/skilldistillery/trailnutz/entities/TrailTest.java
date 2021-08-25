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

class TrailTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Trail trail;
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
		trail = em.find(Trail.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		trail = null;
	}

	@Test
	@DisplayName("Testing Trail entity")
	void test() {
		assertNotNull(trail);
		assertEquals("Grandfather", trail.getCity());
	}
	
	@Test
	@DisplayName("Testing connection between Trail and User")
	void test1() {
		assertNotNull(trail);
		assertTrue(trail.getUsers().size() > 0);
	}
	
	@Test
	@DisplayName("Testing connection between Trail and Comment")
	void test2() {
		assertNotNull(trail);
		assertEquals("I have to agree!", trail.getComments().get(0).getMessage());
	}
	
	@Test
	@DisplayName("Testing connection between Trail and amenity")
	void test3() {
		assertNotNull(trail);
		assertEquals("Bathrooms?", trail.getAmenities().get(0).getDescription());
	}
	
	@Test
	@DisplayName("Testing connection between trail and trail_resource")
	void test4() {
		assertNotNull(trail);
		assertEquals("Trail photos", trail.getTrailResource().get(0).getTitle());
	}
	
	@Test
	@DisplayName("Testing connection between trail and trail_image")
	void test5() {
		assertNotNull(trail);
		assertEquals("https://i.wnc.io/s1024/2011-05-15_grandfather-mountain-state-park_calloway-peak-view-attic-window-peak-hazy.jpg", trail.getTrailImages().get(0).getImageUrl());
	}
	
	@Test
	@DisplayName("Testing connection between trail and difficulty")
	void test6() {
		assertNotNull(trail);
		assertEquals("Moderately Strenuous hikes will generally be challenging for an unconditioned person. The terrain will involve a steady and often steep incline. Generally 5 to 8 miles.", trail.getDifficulty().getDescription());
	}
	
	@Test
	@DisplayName("Testing connection between trail and route_type")
	void test7() {
		assertNotNull(trail);
		assertEquals("Loop", trail.getRouteType().getName());
	}

}
