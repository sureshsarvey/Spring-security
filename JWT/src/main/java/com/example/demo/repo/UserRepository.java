package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Integer>{

	 MyUser findByUsername(String username);
	 
	 @Query("select user.userRole from MyUser user where user.username =:username")
	 String getRoles(@Param("username") String username);
	 
}
