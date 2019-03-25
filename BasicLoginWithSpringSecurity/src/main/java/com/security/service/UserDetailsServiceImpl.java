package com.security.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.dao.UserInfoDao;
import com.security.model.UserInfo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public UserDetailsServiceImpl() {
        super();
    }


	@Autowired
	private UserInfoDao dao;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo  = dao.getUser(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getUserRole());
		User user = new User(userInfo.getUserName(),userInfo.getPassword(),Arrays.asList(authority));
		UserDetails userDetails = (UserDetails) user;
		return userDetails ;
	}
	
	
	

	
	
}
