package com.zbodya.Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="files")
public class File
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="file_id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(mappedBy="files")
	List<Userstory> sprints;
	
}
