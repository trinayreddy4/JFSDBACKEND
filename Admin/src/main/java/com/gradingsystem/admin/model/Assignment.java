package com.gradingsystem.admin.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Assignment {
	public Assignment()
	{
		
	}
	public Assignment(String name, String description, String filePath, LocalDate deadline,int maxMarks) {
		super();
		this.name = name;
		this.description = description;
		this.filePath = filePath;
		this.deadline = deadline;
		this.maxMarks = maxMarks;
	}
	public Assignment(String id, String name, String description, String filePath, int faculty_id, LocalDate deadline,
			int maxMarks) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.filePath = filePath;
		this.faculty_id = faculty_id;
		this.deadline = deadline;
		this.maxMarks = maxMarks;
	}
	public Assignment(String name, String description, String filePath, int faculty_id, LocalDate deadline,
			int maxMarks) {
		super();
		this.name = name;
		this.description = description;
		this.filePath = filePath;
		this.faculty_id = faculty_id;
		this.deadline = deadline;
		this.maxMarks = maxMarks;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	private String filePath; 
	private int faculty_id;
    private LocalDate deadline;
    private int maxMarks=-1;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	
	@Override
	public String toString() {
		return "Assignment [id=" + id + ", name=" + name + ", description=" + description + ", filePath=" + filePath
				+ ", deadline=" + deadline + ", maxMarks=" + maxMarks + "]";
	}
	public int getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}
	public int getFaculty_id() {
		return faculty_id;
	}
	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}
	
	
	
}
