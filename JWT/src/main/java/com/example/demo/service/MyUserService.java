package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.MyUser;

public interface MyUserService {
	
	void saveUser(MyUser user);
	
	String getRoles(String username);

}
