package com.zbodya;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.zbodya.Entities.Userstory;
import com.zbodya.Entities.DTO.UserstoryDTO;
import com.zbodya.Repositories.UserstoryRepository;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestAPITest 
{
	
	@Autowired
	private RestTemplate rest;
	private static final String URL = "http://localhost:8080/sprints/";
	@Autowired
	private UserstoryRepository repo;
	
	@Test
	public void getAllSprintsWithoutUserstories() 
	{
		ResponseEntity<Object[]> sprints =rest.getForEntity("http://localhost:8080/sprints/getAll?tasks=false",Object[].class);
		Arrays.asList(sprints.getBody()).stream().forEach(s->System.out.println(s));
		assertThat(sprints.getBody()).doesNotContainNull();
	}
	
	@Test
	public void getAllSprintsWithUserstories() 
	{
		ResponseEntity<Object[]> sprints =rest.getForEntity("http://localhost:8080/sprints/getAll?tasks=true",Object[].class);
		Arrays.asList(sprints.getBody()).stream().forEach(s->System.out.println(s));		
		assertThat(sprints.getBody()).doesNotContainNull();
	}
	
	@Test
	public void getAllUserstoriesSortedByDescription() 
	{
		ResponseEntity<UserstoryDTO[]> userstories =rest.getForEntity("http://localhost:8080/userstories/getUserstoriesSortByDescription?pageNumber=0&amount=10",UserstoryDTO[].class);
		assertThat(userstories.getBody().length).isEqualTo(7);
	}
	
	@Test
	public void deleteUserstoryById() 
	{
		Assertions.assertNotNull(repo.findById(1L));
		rest.delete("http://localhost:8080/userstories/deleteUserstoryById?id=1");
		Assertions.assertNull(repo.findById(1L).orElse(null));
		
	}
	


}
