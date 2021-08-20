package com.zbodya.Entities;

import java.util.ArrayList;
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
	List<Userstory> userstories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Userstory> getUserstories() {
		return userstories;
	}

	public void setUserstories(List<Userstory> userstories) {
		this.userstories = userstories;
	}

	public File(String name) {
		super();
		this.name = name;
		this.userstories = new ArrayList<>();
	}

	public File() {
		super();
	}
	
	
	
}
