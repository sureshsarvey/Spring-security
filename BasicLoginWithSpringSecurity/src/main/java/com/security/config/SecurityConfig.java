package com.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.security.service.UserDetailsServiceImpl;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Import(HibernateConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
     private UserDetailsServiceImpl userDetailsServiceImpl;
    
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
	/*@Autowired
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		 auth.inMemoryAuthentication()
	        .passwordEncoder(passwordEncoder)
	        .withUser("user").password(passwordEncoder.encode("123456")).roles("USER")
	        .and()
	        .withUser("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN");
		
    }*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
          = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		 http.authorizeRequests()
	        .antMatchers("/login")
	            .permitAll()
	        .antMatchers("/**")
	            .hasAnyRole("ADMIN", "USER")
	        .and()
	            .formLogin()
	            .loginPage("/login")
	            .loginProcessingUrl("/success")
	            .successHandler(new AuthenticationSuccessHandler() {
	                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	                        Authentication authentication) throws IOException, ServletException {
	                    redirectStrategy.sendRedirect(request, response, "/");
	                }})
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

}
