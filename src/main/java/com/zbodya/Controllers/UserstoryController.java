package com.zbodya.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zbodya.Entities.Userstory;
import com.zbodya.Entities.DTO.UserstoryDTO;
import com.zbodya.Service.UserstoryService;

@RestController
@RequestMapping("userstories")
public class UserstoryController 
{
	@Autowired
	UserstoryService service;

	@PostMapping("addNewUserstoryToSprint")
	public ResponseEntity<UserstoryDTO> addNewUserstoryToSprintByID(@RequestParam String description, @RequestParam String status, @RequestParam int countStoryPoints, Long id)
	{
		Userstory userStory = new Userstory(description, countStoryPoints, status);
		return new ResponseEntity<>(service.addNewUserstoryToSprintByID(userStory, id),HttpStatus.CREATED);
	}
	
	@GetMapping("getStorypointsCountFromDoneUserstoriesBySprintID")
	public ResponseEntity<Integer> getStorypointsCountFromDoneUserstoriesBySprintID(@RequestParam Long id)
	{
		return new ResponseEntity<>(service.getCountOfStoryPointsInDoneUserstoriesBySprintID(id),HttpStatus.OK);
	}
	
	@GetMapping("getUserStoryDescriptionByID")
	public ResponseEntity<String> getUserStoryDescriptionByID(@RequestParam Long id)
	{
		return new ResponseEntity<>(service.getUserStoryDescriptionById(id), HttpStatus.OK);
	}
	
	@GetMapping("addFileToUserstoryByID")
	public ResponseEntity<UserstoryDTO> addFileToUserstoryById(@RequestParam Long id, @RequestParam String fileName)
	{
		return new ResponseEntity<>(service.addFileToUserstoryById(id, fileName),HttpStatus.OK);
	}
	
	@GetMapping("getFileByUserstoryID")
	public ResponseEntity<String> getFileByUserstoryID(@RequestParam Long id)
	{		
		return new ResponseEntity<>(service.getFileByUserstoryId(id).stream().collect(Collectors.joining(" ")),HttpStatus.OK);
	}
	
	@DeleteMapping("deleteUserstoryById")
	public ResponseEntity<String> deleteUserstoryById(@RequestParam Long id)
	{
		return new ResponseEntity<>(service.deleteUserstory(id),HttpStatus.OK);
	}
	
	@GetMapping("getUserstoriesSortByDescription")
	public ResponseEntity<List<UserstoryDTO>> getUserstoriesSortByDescription(@RequestParam int pageNumber, @RequestParam int amount)
	{
		return new ResponseEntity<>(service.getUserstoriesSortByDescription(pageNumber, amount), HttpStatus.OK);
	}
}
