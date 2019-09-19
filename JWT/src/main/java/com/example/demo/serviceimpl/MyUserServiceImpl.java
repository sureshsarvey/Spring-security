package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repo.UserRepository;
import com.example.demo.service.MyUserService;
import com.example.demo.vo.MyUser;

@Service
public class MyUserServiceImpl implements MyUserService
{

	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public void saveUser(MyUser user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}


	@Override
	public String getRoles(String username) {
		return userRepo.getRoles(username);
	}

}
