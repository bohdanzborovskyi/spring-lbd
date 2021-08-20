package com.zbodya.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zbodya.Entities.File;
import com.zbodya.Entities.Userstory;

@Repository
public interface UserstoryRepository extends PagingAndSortingRepository<Userstory, Long> 
{
	Userstory save(Userstory userstory);
	List<Userstory> getUserstoriesBySprintsId(Long id);
	List<Userstory> findAll();
	
	@Query("select sum(u.countStoryPoints) from Userstory u where u.status='Done' group by u.status ")
	int getStorypointsCountFromDoneUserstories();
	
	@Query("select sum(u.countStoryPoints) from Userstory u, Sprint s where u.status='Done' and s.id=?1 group by u.status ")
	int getStorypointsCountFromDoneUserstoriesBySprintID(Long id);

	Userstory getDescriptionById(Long id);
	
	@Query("select f.name from File f, Userstory u where u.id=?1")
	List<String> getAllFilesById(Long id);
	
	
}
