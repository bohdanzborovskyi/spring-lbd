package com.zbodya.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbodya.Entities.File;
import com.zbodya.Entities.Userstory;
import com.zbodya.Exceptions.TransactionException;
import com.zbodya.Repositories.UserstoryRepository;

@Service
public class UserstoryService 
{
	
	@Autowired
	UserstoryRepository repo;
	
	@Transactional(rollbackOn = TransactionException.class)
	public void saveUserstory(String description, int countStoryPoints, String status, List<File> files) throws TransactionException 
	{
		if(description!= null && countStoryPoints != 0)
		{
			if(status == null)
			{
				status = "To do";
				Userstory us = new Userstory(description, countStoryPoints, status, files);
				repo.save(us);			
			}else 
			{
				Userstory us = new Userstory(description, countStoryPoints, status, files);
				repo.save(us);	
			}
		}
		else
			throw new TransactionException("Invalid Userstory data");
	}
	
	public List<Userstory> getUserstoriesBySpringID(Long id)
	{
		return repo.getUserstoriesBySprintsId(id);
	}
	
	public int countOfStorypoints() 
	{
		return repo.getStorypointsCountFromDoneUserstories();
	}

}
