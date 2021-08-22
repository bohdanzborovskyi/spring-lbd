package com.zbodya.Controllers;

import org.jboss.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SecurityController 
{
	
	private Logger LOG = Logger.getLogger(getClass().getName());	
	@GetMapping("/testSecurity")
	public String testSecurity() 
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		LOG.info("Name: " + authentication.getName());
		LOG.info("Role: " + authentication.getAuthorities());
		return "Name: " +  authentication.getName() + " \n Role: " + authentication.getAuthorities();
	}

}
