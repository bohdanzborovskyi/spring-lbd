package com.zbodya.Entities.DTO;


public class UserstoryDTO
{
	private String description;
	private int countStory;
	
	public UserstoryDTO(String description, int countStory) {
		super();	
		this.description = description;
		this.countStory = countStory;
	}

	public UserstoryDTO() {
		super();
	}	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCountStory() {
		return countStory;
	}

	public void setCountStory(int countStory) {
		this.countStory = countStory;
	}
	
	
	
}

