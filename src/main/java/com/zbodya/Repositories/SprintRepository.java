package com.zbodya.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zbodya.Entities.Sprint;

@Repository
public interface SprintRepository extends PagingAndSortingRepository<Sprint, Long> 
{

	Sprint save(Sprint sprint);
	List<Sprint>findAll();
	@Query("Select s from Sprint s  where s.startdate<?1 and s.enddate>?2")
	List<Sprint> findAllByDateBetween(LocalDate start, LocalDate end);
	
	
	

}
