package com.skilldistillery.trailnutz.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

class TrailResourceTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private TrailResource trailResrc;
	
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
		trailResrc = em.find(TrailResource.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		trailResrc = null;
	}

	@Test
	@DisplayName("trail resource entity test")
	void test() {
		LocalDateTime createDate =  LocalDateTime.of(2020, 01, 01, 10, 10, 00);
		
		assertNotNull(trailResrc);
		assertEquals("Trail photos", trailResrc.getTitle());
		assertEquals(createDate, trailResrc.getCreatedAt());
		
	}
	
	@Test
	@DisplayName("trail resource to user test")
	void test1() {
		
		assertNotNull(trailResrc.getUser());
		assertEquals(1, trailResrc.getUser().getId());
		assertEquals("Gerry", trailResrc.getUser().getFirstName());
		
	}
	
	@Test
	@DisplayName("trail resource to trail test")
	void test2() {
		
		assertNotNull(trailResrc.getTrail());
		assertEquals(1, trailResrc.getTrail().getId());
		assertEquals("Racoons", trailResrc.getTrail().getWildlife());
		
	}
	

}
