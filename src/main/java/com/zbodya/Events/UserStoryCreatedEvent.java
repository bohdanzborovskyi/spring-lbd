package com.zbodya.Events;

import org.springframework.context.ApplicationEvent;

import com.zbodya.Entities.Userstory;

public class UserStoryCreatedEvent extends ApplicationEvent {

	private Userstory userStory;
	
	public UserStoryCreatedEvent(Object source, Userstory userstory) 
	{
		super(source);
		this.userStory = userstory;
	}

	public Userstory getUserStory() {
		return userStory;
	}

	public void setUserStory(Userstory userStory) {
		this.userStory = userStory;
	}
	
	

}
