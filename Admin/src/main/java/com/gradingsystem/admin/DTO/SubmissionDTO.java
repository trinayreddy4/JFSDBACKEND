package com.gradingsystem.admin.DTO;

import java.time.Instant;

public class SubmissionDTO {
	
	public SubmissionDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public SubmissionDTO(int id, int marks, String assignmentId, Instant submittedOn, String feedBack) {
		super();
		this.id = id;
		this.marks = marks;
		AssignmentId = assignmentId;
		this.submittedOn = submittedOn;
		this.feedBack = feedBack;
	}
	public SubmissionDTO(int id, int marks, String assignmentId, Instant submittedOn) {
		super();
		this.id = id;
		this.marks = marks;
		AssignmentId = assignmentId;
		this.submittedOn = submittedOn;
	}
	public SubmissionDTO(int i,int marks ,Instant submittedOn) {
		super();
		this.id = i;
		this.marks = marks;
		this.submittedOn = submittedOn;
	}
	private int id;
	private int marks;
	private String AssignmentId;
	private Instant submittedOn;
	private String feedBack="";
	
	public Instant getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(Instant submittedOn) {
		this.submittedOn = submittedOn;
	}
	@Override
	public String toString() {
		return "SubmissionDTO [id=" + id + ", submittedOn=" + submittedOn + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getAssignmentId() {
		return AssignmentId;
	}
	public void setAssignmentId(String assignmentId) {
		AssignmentId = assignmentId;
	}
	public String getFeedBack() {
		return feedBack;
	}
	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	
	
}
