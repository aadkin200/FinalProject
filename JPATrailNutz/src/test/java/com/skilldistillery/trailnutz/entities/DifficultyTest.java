package com.skilldistillery.trailnutz.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DifficultyTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Difficulty difficulty;
	
	
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
		difficulty = em.find(Difficulty.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		difficulty = null;
	}

	@Test
	void test_difficulty_name_mapping() {
		assertNotNull(difficulty);
		assertEquals("Easy", difficulty.getName());
	}


	
}
