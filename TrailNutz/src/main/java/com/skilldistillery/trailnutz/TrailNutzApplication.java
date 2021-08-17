package com.skilldistillery.trailnutz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TrailNutzApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailNutzApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder configuePasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
