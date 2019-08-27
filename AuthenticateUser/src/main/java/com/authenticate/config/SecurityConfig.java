package com.authenticate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.authenticate.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
     
	
	 /*@Autowired
	 PasswordEncoder passwordEncoder;*/
	 
	 @Autowired
	 private CustomAuthenticationProvider  customAuthenticationProvider;
	 
	 //private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
	 @Autowired
	 private UserDetailsServiceImpl userDetailsService;
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.inMemoryAuthentication()
	        .passwordEncoder(passwordEncoder)
	        .withUser("suresh").password(passwordEncoder.encode("123456")).roles("USER")
	        .and()
	        .withUser("teja").password(passwordEncoder.encode("12345")).roles("USER", "ADMIN");
	}*/
	 
	 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		 auth.authenticationProvider(customAuthenticationProvider);
	}
	
	/*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
	
	/*
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		 http.authorizeRequests()
	        .antMatchers("/login")
	            .permitAll()
	        .antMatchers("/**")
	            .authenticated()
	        .and()
	            .formLogin()
	            .loginPage("/login")
	            .usernameParameter("username")
	            .passwordParameter("password")
	            .loginProcessingUrl("/processUserCredentials")
	            .successHandler(new MySimpleUrlAuthenticationSuccessHandler())
	            .failureUrl("/login?error=true")
	            .permitAll()
	        .and()
	            .logout()
	            .logoutSuccessUrl("/login?logout=true")
	            .invalidateHttpSession(true)
	            .permitAll()
	        .and()
	            .csrf()
	            .disable();
            
		 
		 
	}
	 */
	 @Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
	        .antMatchers("/login")
	            .permitAll()
	         .antMatchers("/authenticateUser")
	         .permitAll()
	        .antMatchers("/**")
	            .authenticated().and()
	            .logout()
	            .logoutSuccessUrl("/login?logout=true")
	            .invalidateHttpSession(true)
	            .permitAll()
	        .and()
	            .csrf()
	            .disable();
         
		 
		 
	}
	 @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	 
	 
}
