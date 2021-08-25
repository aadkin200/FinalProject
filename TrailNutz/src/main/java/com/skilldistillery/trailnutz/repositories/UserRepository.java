package com.skilldistillery.trailnutz.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.trailnutz.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	@Query(value="SELECT Count(*) from user u  join user_has_trail on u.id = user_id JOIN trail t ON trail_id = t.id where u.id = ?1 AND t.id = ?2",
			nativeQuery = true)
	long getUserFavorites(int userId, int trailId);
	
}
