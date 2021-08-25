package com.skilldistillery.trailnutz.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrailImageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private TrailImage trailImage;
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
		trailImage = em.find(TrailImage.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		trailImage = null;
	}

	@Test
	@DisplayName("trailImage entity test")
	void test() {
		assertNotNull(trailImage);
		assertEquals("https://i.wnc.io/s1024/2011-05-15_grandfather-mountain-state-park_calloway-peak-view-attic-window-peak-hazy.jpg", trailImage.getImageUrl());
	}
	
	@Test
	@DisplayName("Testing trailImage to trail mapping")
	void test1() {
		assertNotNull(trailImage);
		assertEquals("Grandfather", trailImage.getTrail().getCity());
	}
	
	@Test
	@DisplayName("Testing trailImage to user")
	void test2() {
		assertNotNull(trailImage);
		assertEquals("Lowkey", trailImage.getUser().getLastName());
	}
	

}
