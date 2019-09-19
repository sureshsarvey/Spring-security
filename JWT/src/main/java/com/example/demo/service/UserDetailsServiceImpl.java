package com.example.demo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.repo.UserRepository;
import com.example.demo.vo.MyUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	//@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = userRepository.findByUsername(username);
		if(user == null)
		{
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}else {
			GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole());
			return new User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
		}
	}

}
