package com.zbodya.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbodya.Entities.Sprint;
import com.zbodya.Entities.Userstory;
import com.zbodya.Exceptions.TransactionException;
import com.zbodya.Repositories.SprintRepository;
import com.zbodya.Repositories.UserstoryRepository;

@Service
public class SprintService 
{
	
	List<String> statuses = Arrays.asList("Pending","In progress", "Finished", "Canceled");
	
	
	@Autowired
	private SprintRepository repo;
	
	@Autowired
	private UserstoryRepository userRepo;

	@Transactional(rollbackOn = TransactionException.class)
	public void saveSprint(String name, LocalDate startDate, LocalDate endDate, String status) throws TransactionException 
	{
		if(endDate.isAfter(startDate) && statuses.contains(status) && name!=null)
		{
			Sprint sprint = new Sprint(name,startDate,endDate,status);
			System.out.println(sprint.getId());
			repo.save(sprint);
		}
		else
			throw new TransactionException("Invalid Sprint data");
	}
	
	public List<Sprint> getAllByDateBetween(LocalDate start, LocalDate end)
	{
		return repo.findAllByDateBetween(start, end);
	}
	
	@Transactional
	public void createSprintWithSomeUserstories() 
	{
		Userstory us1 = new Userstory("Userstory First", 2, "In progress");
		Userstory us2 = new Userstory("Userstory Second", 4, "To do");
		Userstory us3 = new Userstory("Userstory Third", 9, "Done");		
		List<Userstory> stories = Arrays.asList(us1, us2, us3);
		userRepo.saveAll(stories);
		Sprint sprint = new Sprint("New sprint", LocalDate.of(2020, 2, 2), LocalDate.of(2021, 11, 11),"Pending");
		repo.save(sprint);
		sprint.addUserstory(us1);
		sprint.addUserstory(us2);
		sprint.addUserstory(us3);
		repo.save(sprint);

	}

	
	
	
}
