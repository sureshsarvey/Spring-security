package com.authenticate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.authenticate.model.User;
import com.authenticate.service.UserService;

@Controller
public class AuthenticateUserController {
	

	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
    private UserService userService;
	
	@GetMapping("/")
	public String getRegisterPage() {
		return "registeruser";
	}
	
	@GetMapping("/admin")
	public String getAdminPage() {
		return "admin";
	}

	@GetMapping("/user")
	public String getUserPage() {
		return "user";
	}

	@GetMapping("/success")
	public String getSuccessPage() {
		return "success";
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(HttpServletRequest request) {
		try {

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String userrole = request.getParameter("userrole");
			User user = new User(username, password, userrole);
			userService.saveUser(user);
			return "login";

		} catch (Exception e) {
			e.printStackTrace();
			return "registeruser";

		}
	}
	
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
	@RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
    public void processUserCredentials(HttpServletRequest request) {
		    try {
		    	 
		    	 String username = request.getParameter("username");
	             String password = request.getParameter("password");
	             Authentication token  = new UsernamePasswordAuthenticationToken(username,password);
	             Authentication authentication = authenticationManager.authenticate(token);
	             SecurityContextHolder.getContext().setAuthentication(authentication);
		    }catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
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
