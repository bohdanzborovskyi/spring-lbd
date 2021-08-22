package com.zbodya.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zbodya.Entities.File;
import com.zbodya.Entities.Sprint;
import com.zbodya.Entities.Userstory;
import com.zbodya.Entities.DTO.UserstoryDTO;
import com.zbodya.Events.UserStoryCreatedEvent;
import com.zbodya.Exceptions.TransactionException;
import com.zbodya.Repositories.FileRepository;
import com.zbodya.Repositories.SprintRepository;
import com.zbodya.Repositories.UserstoryRepository;

@Service
public class UserstoryService 
{
	
	@Autowired
	UserstoryRepository repo;
	
	@Autowired 
	SprintRepository sprintRepo;
	
	@Autowired 
	FileRepository fileRepo;
	
	@Autowired 
	DTOMapper mapper;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional(rollbackOn = TransactionException.class)
	public void saveUserstory(String description, int countStoryPoints, String status, List<File> files) throws TransactionException 
	{
		if(description!= null && countStoryPoints != 0)
		{
			Userstory us;
			if(status == null)
			{
				status = "To do";
				us = new Userstory(description, countStoryPoints, status, files);
				repo.save(us);			
			}else 
			{
				us = new Userstory(description, countStoryPoints, status, files);
				repo.save(us);	
			}
			UserStoryCreatedEvent event = new UserStoryCreatedEvent(this,us);
			publisher.publishEvent(event);
		}
		else
			throw new TransactionException("Invalid Userstory data");		
		
	}
	
	public List<Userstory> getUserstoriesBySprintID(Long id)
	{
		return repo.getUserstoriesBySprintsId(id);
	}
	
	public int countOfStorypoints() 
	{
		return repo.getStorypointsCountFromDoneUserstories();
	}
	
	public UserstoryDTO addNewUserstoryToSprintByID(Userstory userStory, Long id) 
	{
		Optional<Sprint> sprint = sprintRepo.findById(id);
		System.out.println("Adding to this sprint " + sprint.get().getDescription());
		sprint.get().addUserstory(userStory);
		repo.save(userStory);
		sprintRepo.save(sprint.get());
		return mapper.convertUserstoryToUserstoryDTO(userStory);
	}
	
	public Integer getCountOfStoryPointsInDoneUserstoriesBySprintID(Long id) 
	{
		return repo.getStorypointsCountFromDoneUserstoriesBySprintID(id);
	}
	
	public String getUserStoryDescriptionById(Long id) 
	{
		return repo.getDescriptionById(id).getDescription();
	}
	
	public UserstoryDTO addFileToUserstoryById(Long id, String fileName) 
	{
		File f = new File(fileName);
		fileRepo.save(f);
		Userstory userS = repo.findById(id).get();
		userS.addFile(f);
		repo.save(userS);
		return mapper.convertUserstoryToUserstoryDTO(userS);
	}
	
	public List<String> getFileByUserstoryId(Long id) 
	{
		return repo.getAllFilesById(id);
	}
	
	public String deleteUserstory(Long id) 
	{
		String s = repo.findById(id).get().getDescription().toString();
		Userstory user = repo.findById(id).get();
		for(Sprint sprint : user.getSprints()) 
		{
			sprint.getUserstories().remove(user);
		}
		user.getSprints().clear();
		
		repo.save(user);
		repo.delete(user);
		return s;
	}
	
	public List<UserstoryDTO> getUserstoriesSortByDescription(int pageNumber, int amount)
	{
		PageRequest page = PageRequest.of(pageNumber, amount, Sort.by("description"));
		for(UserstoryDTO dto : repo.findAll(page).stream().map(u -> mapper.convertUserstoryToUserstoryDTO(u)).toList()) 
		{
			System.out.println(dto.toString());
		}
		return repo.findAll(page).stream().map(u -> mapper.convertUserstoryToUserstoryDTO(u)).toList();
	}

}
