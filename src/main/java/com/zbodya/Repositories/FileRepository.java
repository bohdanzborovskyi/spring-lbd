package com.zbodya.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zbodya.Entities.File;

@Repository
public interface FileRepository extends CrudRepository<File, Long> 
{

}
