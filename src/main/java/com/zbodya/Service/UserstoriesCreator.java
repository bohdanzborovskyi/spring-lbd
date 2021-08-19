package com.zbodya.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zbodya.Exceptions.TransactionException;

@Component
public class UserstoriesCreator 
{

	@Autowired
	UserstoryService service;
	
	public void create100Userstories() 
	{			
		for(int i=0;i<100;i++) 
		{
			try 
			{
				if(i%3==0) 		
					service.saveUserstory("Story number " + i, i+1, "In progress",null);	
				else if(i%4==0)
					service.saveUserstory("Story number " + i, i+1, "Review",null);
				else if(i%2==0)					
					service.saveUserstory("Story number " + i, i+1, "To do",null);
				else 
					service.saveUserstory("Story number " + i, i+1, "Done",null);	
			}catch(TransactionException ex) 
			{
				System.out.println("Creating not finished! " + ex.getMessage());
			}
		}
	}
	
}
