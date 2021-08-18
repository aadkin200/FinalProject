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

class AmenityTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Amenity amenity;
	
	
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
		amenity = em.find(Amenity.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		amenity = null;
	}

	@Test
	void test_amenity_name_mapping() {
		assertNotNull(amenity);
		assertEquals("Bathroom", amenity.getName());
	}

	@Test
	@DisplayName("amenity to trail test")
	void test2() {
		
		assertNotNull(amenity.getTrails());
		assertEquals(1, amenity.getTrails().size());
	
		
	}
	

}
