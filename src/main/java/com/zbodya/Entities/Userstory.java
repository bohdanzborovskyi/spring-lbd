package com.zbodya.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="userstories")
public class Userstory 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userstory_id")
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="count_story_points")
	private int countStoryPoints;
	
	@Column(name="status")
	private String status;
	
	@ManyToMany(mappedBy="userstories", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	List<Sprint> sprints;
	
	@ManyToMany
	@JoinTable(name="userstory_file", joinColumns = @JoinColumn(name="userstory_id"),
				inverseJoinColumns = @JoinColumn(name="file_id"))
	List<File>files;

	public Long getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCountStoryPoints() {
		return countStoryPoints;
	}

	public void setCountStoryPoints(int countStoryPoints) {
		this.countStoryPoints = countStoryPoints;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}		

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public Userstory(String description, int countStoryPoints, String status) {
		super();
		this.description = description;
		this.countStoryPoints = countStoryPoints;
		this.status = status;
		this.files = new ArrayList<File>();
		this.sprints = new ArrayList<Sprint>();
	}
	
	public Userstory(String description, int countStoryPoints, String status, List<File>files) {
		super();
		this.description = description;
		this.countStoryPoints = countStoryPoints;
		this.status = status;
		this.files = files;
		this.sprints = new ArrayList<Sprint>();
	}
	
	public Userstory() {}
	
	public void addFile(File file) 
	{
		this.files.add(file);
		file.userstories.add(this);
	}

}
