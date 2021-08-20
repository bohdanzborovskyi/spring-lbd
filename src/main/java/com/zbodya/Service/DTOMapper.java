package com.zbodya.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.zbodya.Entities.Sprint;
import com.zbodya.Entities.Userstory;
import com.zbodya.Entities.DTO.SprintDTO;
import com.zbodya.Entities.DTO.SprintWithoutUserstoryDTO;
import com.zbodya.Entities.DTO.UserstoryDTO;


@Service
public class DTOMapper 
{

	@Bean
	public ModelMapper getMapper() 
	{
		return new ModelMapper();
	}
	
	@Autowired
	ModelMapper mapper;
	
	public SprintDTO convertSprintToSprintDTO(Sprint sprint) 
	{
		return mapper.map(sprint, SprintDTO.class);
	}
	
	public Sprint convertSprintDTOToSprint(SprintDTO sprintDTO) 
	{
		return mapper.map(sprintDTO, Sprint.class);
	}
	
	public UserstoryDTO convertUserstoryToUserstoryDTO(Userstory userstory) 
	{
		return mapper.map(userstory, UserstoryDTO.class);
	}
	
	public Userstory convertUserstoryDTOToUserstory(UserstoryDTO userstoryDTO) 
	{
		return mapper.map(userstoryDTO, Userstory.class);
	}
	
	public SprintWithoutUserstoryDTO convertSprintWithoutUserstoryToSprintWithoutUserstoryDTO(Sprint sprint) 
	{
		return mapper.map(sprint, SprintWithoutUserstoryDTO.class);
	}
	
	public Sprint convertSprintWithoutUserstoryDTOToSprintWithoutUserstory(SprintWithoutUserstoryDTO sprintWithotUserstoryDTO) 
	{
		return mapper.map(sprintWithotUserstoryDTO, Sprint.class);
	}
	
}
