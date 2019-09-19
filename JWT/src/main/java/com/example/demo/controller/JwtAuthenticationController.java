package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.service.MyUserService;
import com.example.demo.vo.JwtRequest;
import com.example.demo.vo.JwtResponse;
import com.example.demo.vo.MyUser;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${jwt.header}")
    private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));

	}
	
	@PostMapping("/saveCredentials")
	public ResponseEntity<?> saveCredentials(@RequestBody MyUser user)
	{
		try {
			
			 user.setPassword(passwordEncoder.encode(user.getPassword()));
			 myUserService.saveUser(user);
			 return ResponseEntity.ok("user saved successfully");
		}catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

	}
	
	@GetMapping("/getRoles")
	public ResponseEntity<Object> getRoles(HttpServletRequest request)
	{
		String token = request.getHeader(tokenHeader).substring(7);
		String username = request.getParameter("username");
		if(!StringUtils.isEmpty(token))
		{
			try {
				
				String role = myUserService.getRoles(username);
				return ResponseEntity.ok().body(role);
				
			}catch(UsernameNotFoundException u)
			{
				System.out.println(u);
				return ResponseEntity.badRequest().body("username not found");
			}
		}
		
		return ResponseEntity.badRequest().body(null);
	
	}

}
