package com.basicJWTProjectBack.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basicJWTProjectBack.Models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	//User findByEmail(String email);
	Optional<User> findByUsername(String username);
}
