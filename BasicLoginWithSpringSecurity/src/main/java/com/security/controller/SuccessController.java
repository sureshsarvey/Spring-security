package com.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SuccessController {
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }
	/*
	@RequestMapping(value="/processAction",method=RequestMethod.GET)
	public String getSuccessPage(HttpServletRequest request,HttpServletResponse response,AuthenticationManager manager) throws IOException, ServletException{
		System.out.println("check for authentication");
		String username  = request.getParameter("username");
		String password = request.getParameter("password");
		try{
		Authentication token = new UsernamePasswordAuthenticationToken(
				username,password,AuthorityUtils.createAuthorityList("USER"));
		Authentication authentication = manager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("request came for process action");
		return "success";
	}catch(Exception e)
	{
		System.out.println("exception");
		e.printStackTrace();
		return "login";
	}
		
	}*/
	
	@GetMapping("/")
	public ModelAndView getSuccessPage(){
		return new ModelAndView("success");
	}
	
	@RequestMapping("/homepage")
	public String getHomePage(){
		System.out.println("requested for homepage");
		return "homepage";
	}
	
	@RequestMapping("/admin")
	public String getAdminPage(){
		System.out.println("requested for homepage");
		return "admin";
	}
	
	 @RequestMapping(value="/logout", method = RequestMethod.GET)
	    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null){   
	            new SecurityContextLogoutHandler().logout(request, response, auth);
	        }
	        return "redirect:/login?logout=true";
	    }

	
	

}
