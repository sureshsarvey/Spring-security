package com.authenticate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authenticate.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findUserByUsername(String username);

}
