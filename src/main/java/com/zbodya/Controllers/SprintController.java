package com.zbodya.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zbodya.Entities.Sprint;
import com.zbodya.Entities.DTO.DTO;
import com.zbodya.Entities.DTO.SprintDTO;
import com.zbodya.Repositories.SprintRepository;
import com.zbodya.Service.DTOMapper;
import com.zbodya.Service.SprintService;

@RestController
@RequestMapping("/sprints")
public class SprintController
{
	
	@Autowired
	SprintRepository sprintRepo;
	
	@Autowired 
	DTOMapper mapper;
	
	@Autowired 
	SprintService service;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<? extends DTO>> getAllSprints(@RequestParam boolean tasks)
	{		if(tasks==true)
				return new ResponseEntity<>(sprintRepo.findAll().stream().map(s -> mapper.convertSprintToSprintDTO(s)).toList(),HttpStatus.OK);
			else	
				return new ResponseEntity<>(sprintRepo.findAll().stream().map(s -> mapper.convertSprintWithoutUserstoryToSprintWithoutUserstoryDTO(s)).toList(),HttpStatus.OK);
	}
	
	@PutMapping("/updateSprintStatus")
	public ResponseEntity<SprintDTO> updateSprintStatus(Long id, String status)
	{		
		return new ResponseEntity(service.updateSprintStatusById(id, status),HttpStatus.OK);
	}
	
	@GetMapping("/getSprinsBetweenDates")
	public ResponseEntity<List<SprintDTO>> getSprinsBetweenDate(@RequestParam String from, @RequestParam String to)
	{
		return new ResponseEntity<>(service.getSprintsBetweenDate(LocalDate.parse(from), LocalDate.parse(to)),HttpStatus.OK);
	}

}
