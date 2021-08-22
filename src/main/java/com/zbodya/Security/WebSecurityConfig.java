package com.zbodya.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity ( securedEnabled = true )
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{		
		http.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and().formLogin().and().httpBasic();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception
	{
		auth.inMemoryAuthentication()
			.withUser("user")
			.password(("{noop}user"))
			.authorities("ROLE_USER")
			.and()		
			.withUser("admin")
			.password(("{noop}admin")).authorities("ROLE_ADMIN")			
			.and()
			.withUser("user2")
			.password(("{noop}user2"))
			.authorities("ROLE_USER");
			
	}
	
//	@Bean
//	public PasswordEncoder encoder() 
//	{
//		return new BCryptPasswordEncoder();
//	}

	
	
}
	