package com.skilldistillery.trailnutz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.trailnutz.entities.Difficulty;

public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {

}
