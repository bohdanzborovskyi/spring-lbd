package com.zbodya;

import java.time.LocalDate;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.zbodya.Entities.Sprint;
import com.zbodya.Entities.Userstory;
import com.zbodya.Exceptions.TransactionException;
import com.zbodya.Repositories.SprintRepository;
import com.zbodya.Repositories.UserstoryRepository;
import com.zbodya.Service.DTOMapper;
import com.zbodya.Service.ExchangeService;
import com.zbodya.Service.SprintService;
import com.zbodya.Service.UserstoryService;

@SpringBootApplication()
public class Day2Application {
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) 
	{
	   return builder.build();
	}
	
	@Bean
	public ModelMapper modelMapper() 
	{
		return new ModelMapper();
	}
	
	@Autowired
	SprintService sprintServ;
	
	@Autowired
	UserstoryService userstoryServ;
	
	@Autowired 
	UserstoryRepository useRepo;
	
	@Autowired
	SprintRepository sprintRepo;
	
	@Autowired
	RestTemplate rest;
	
	@Autowired 
	DTOMapper mapper;
	
	@Autowired
	ExchangeService exchange;
	
	Logger LOG =    LogManager.getLogger(Day2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Day2Application.class, args);	
	}
	@PostConstruct
	public void postConstruct() 
	{
		// Saving sprint and userstory
			try {
				sprintServ.saveSprint("Another Sprint", LocalDate.of(2020, 9, 9), LocalDate.of(2021, 9, 9),"Pending");
				userstoryServ.saveUserstory("Another userstory", 10, null, null);

			} catch (TransactionException e)
			{								
				LOG.info(e.getMessage());
			}
		// Adding relation between entities	
			
			Optional<Sprint> sprint = sprintRepo.findById(1L);
			System.out.println("Sprint: " + sprint.get().getName());
			Optional<Userstory> one = useRepo.findById(1L);
			System.out.println("Userstory: " + one.get().getDescription());
			Optional<Userstory> two = useRepo.findById(2L);
			System.out.println("Userstory: " + two.get().getDescription());
			sprint.get().addUserstory(one.get());
			sprint.get().addUserstory(two.get());
			sprintRepo.save(sprint.get());
			
		
		// Create sprints with userstories	
			
			sprintServ.createSprintWithSomeUserstories();			
	}

}
