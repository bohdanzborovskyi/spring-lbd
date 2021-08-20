package com.zbodya.Entities.DTO;

import java.util.List;

public class SprintWithoutUserstoryDTO implements DTO
{

	private String name;
	private String status;
	
	public SprintWithoutUserstoryDTO() {};
	
	public SprintWithoutUserstoryDTO(String name, String status) 
	{
		this.name = name;
		this.status = status;
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

	
	public String toString() 
	{
		return "Name: " +  this.name + " status: "  + this.status;
				
	}	 
	
	
}
