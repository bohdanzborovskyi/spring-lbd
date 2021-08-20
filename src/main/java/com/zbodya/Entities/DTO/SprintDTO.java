package com.zbodya.Entities.DTO;

import java.time.LocalDate;
import java.util.List;

public class SprintDTO implements DTO
{
	private String name;
	private String status;
	private List<UserstoryDTO>userstories;
	private LocalDate startdate;
	private LocalDate enddate;
	
	public SprintDTO() {};
	
	public SprintDTO(String name, String status, List<UserstoryDTO> userstories) 
	{
		this.name = name;
		this.status = status;
		this.userstories = userstories;
	}
	
	public SprintDTO(String name, String status, List<UserstoryDTO> userstories, LocalDate startdate,
			LocalDate enddate) {
		super();
		this.name = name;
		this.status = status;
		this.userstories = userstories;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserstoryDTO> getUserstories() {
		return userstories;
	}

	public void setUserstories(List<UserstoryDTO> userstories) {
		this.userstories = userstories;
	}	
	
	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartDate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public String toString() 
	{
		return "Name: " +  this.name + " status: "  + this.status + " start date: " + this.startdate + " end date: " + this.enddate +
				" All userstories: \n" + this.userstoriesToString(); 
	}
	 
	public String userstoriesToString() 
	{
		String result = "";
		for(UserstoryDTO userstory: this.userstories) 
		{
			result += "Description: " + userstory.getDescription() + " count of story points: " + userstory.getCountStory() + " \n";
		}
		return result; 
	}
	
	
	
	
	

}
