package com.zbodya;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.zbodya.Entities.Sprint;
import com.zbodya.Entities.Userstory;
import com.zbodya.Exceptions.TransactionException;
import com.zbodya.Repositories.SprintRepository;
import com.zbodya.Repositories.UserstoryRepository;
import com.zbodya.Service.SprintService;
import com.zbodya.Service.UserstoriesCreator;
import com.zbodya.Service.UserstoryService;

@SpringBootTest
class Day2ApplicationTests 
{

	@Autowired 
	UserstoryRepository userstoryRepo;
	
	@Autowired
	SprintService sprintServ;
	
	@Autowired
	UserstoryService userstoryServ;
	
	@Autowired 
	UserstoriesCreator usc;
	
	@Autowired
	SprintRepository sprintRepo;
	
	@Test
	void contextLoads() 
	{
	}
	
	@Test
	void testFindUserstoriesBySprintID() 
	{
		assertThat(userstoryServ.getUserstoriesBySprintID(1L).size()).isEqualTo(2);
	}
	
	@Test
	void testSaveSprintByWrongStatus() 
	{
		Assertions.assertThrows(TransactionException.class, ()->sprintServ.saveSprint("None", LocalDate.of(2020, 9, 9), LocalDate.of(2021, 9, 9), null));
	}
	
	@Test
	void testSaveSprintByIncorrectDates() 
	{
		Assertions.assertThrows(TransactionException.class, ()->sprintServ.saveSprint("None", LocalDate.of(2020, 9, 9), LocalDate.of(2019, 9, 9), null));
	}
	
	@Test
	void testSaveSprintByNullName() 
	{
		Assertions.assertThrows(TransactionException.class, ()->sprintServ.saveSprint(null, LocalDate.of(2020, 9, 9), LocalDate.of(2021, 9, 9), null));
	}
	
	@Test
	void testSaveUserStoryByNullName() 
	{
		Assertions.assertThrows(TransactionException.class, ()->userstoryServ.saveUserstory(null, 2, "To do", null));
	}
	
	@Test 
	void testGetAllSprintsBetweenDates() 
	{
		assertThat(sprintServ.getAllByDateBetween(LocalDate.of(2020, 10, 4), LocalDate.of(2020, 11, 5)).size()).isEqualTo(1);
	}
	
	@Test
	void testGetCountOfStoryPointsFromUserstoriesDone() 
	{
		assertThat(userstoryServ.countOfStorypoints()).isEqualTo(1673);
	}
	
	@Test
	void checkCreating100Userstories() 
	{		
		usc.create100Userstories();
		assertThat(userstoryRepo.findAll().size()).isEqualTo(104);
	}
	
	@Test
	void checkIfUserstoryPaginationWorkCorrectly() 
	{
		Page<Userstory> userstories = userstoryRepo.findAll(PageRequest.of(0, 20));
		assertThat(userstories.getSize()).isEqualTo(20);
	}
	
	@Test
	void checkIfSprintPaginationAndSortingWorkCorrectly() 
	{
		Pageable sortByStartDateFirstFive = PageRequest.of(0, 5, Sort.by("startdate").descending());
		Page<Sprint> sprints = sprintRepo.findAll(sortByStartDateFirstFive);
		assertThat(sprints.getContent().get(0).getStartdate().isAfter(sprints.getContent().get(1).getStartdate()));
		assertThat(sprints.getSize()).isEqualTo(5);
	}
	
	
	
	
	
	
	
	

}
