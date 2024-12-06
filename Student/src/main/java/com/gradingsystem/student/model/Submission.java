package com.gradingsystem.student.model;


import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Submission {
	
	public Submission(int id, String filepath,  String assignmentId, Instant submittedOn) {
		super();
		this.student_id = id;
		this.filepath = filepath;
		AssignmentId = assignmentId;
		this.submittedOn = submittedOn;
	}

	public Submission(String id, String filepath, int student_id) {
		super();
		this.id = id;
		this.filepath = filepath;
		this.student_id = student_id;
	}

	public Submission()
	{
		
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(name="submission")
	private String filepath;
	private int student_id;
	private String AssignmentId;
	private Instant submittedOn = Instant.now();
	
	private int marksAwarded=-1;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	@Override
	public String toString() {
		return "Submission [id=" + id + ", filepath=" + filepath + ", student_id=" + student_id + ", AssignmentId="
				+ AssignmentId + ", submittedOn=" + submittedOn + ", marksAwarded=" + marksAwarded + "]";
	}

	public int getMarksAwarded() {
		return marksAwarded;
	}

	public void setMarksAwarded(int marksAwarded) {
		this.marksAwarded = marksAwarded;
	}

	public Instant getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Instant submittedOn) {
		this.submittedOn = submittedOn;
	}

	public String getAssignmentId() {
		return AssignmentId;
	}

	public void setAssignmentId(String assignmentId) {
		AssignmentId = assignmentId;
	}
}
