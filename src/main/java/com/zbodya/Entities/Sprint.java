package com.zbodya.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="sprints")
public class Sprint 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sprint_id")
	private Long id;
	
	@Column(nullable=false, length=20)
	private String name;
	
	@Column(name="start_date")
	private LocalDate startdate;
	
	@Column(name="end_date")
	private LocalDate enddate;
	
	@Column(name="description", length=50)
	private String description;
	
	@Column(name="status")
	private String status;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name="sprint_userstory", joinColumns= @JoinColumn(name="sprint_id"),
				inverseJoinColumns = @JoinColumn(name="userstory_id"))
	List<Userstory>userstories;	

	public Long getId() {
		return id;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getEndtdate() {
		return enddate;
	}

	public void setEndtdate(LocalDate endtdate) {
		this.enddate = endtdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Userstory> getUserstories() {
		return userstories;
	}

	public void setUserstories(List<Userstory> userstories) {
		this.userstories = userstories;
	}

	
	public Sprint(String name, LocalDate startdate, LocalDate endtdate, String status) {
		super();
		this.name = name;
		this.startdate = startdate;
		this.enddate = endtdate;
		this.status = status;
		this.description = null;
		this.userstories = new ArrayList<Userstory>();
	}

	public Sprint() {
		super();
	}
	
	public void addUserstory(Userstory us) 
	{
		us.getSprints().add(this);
		this.userstories.add(us);
	}
	
	
	
	
}
