package com.authenticate.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.authenticate.service.UserDetailsServiceImpl;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Autowired
	 PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		        String username = authentication.getName();
		        String password = (String)authentication.getCredentials();
		        
		        UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
		        if(userDetails == null || !userDetails.getUsername().equalsIgnoreCase(username))
		        {
		        	 System.out.println("User not exist's in our database");
		        	 throw new BadCredentialsException("Username not found.");
		        }
		        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
		        	System.out.println("password is not valid");
	                throw new BadCredentialsException("Wrong password.");
	            }
		        System.out.println("User found in database");
		        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		        
	            return new UsernamePasswordAuthenticationToken(username, password, authorities);
		        
		        
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
