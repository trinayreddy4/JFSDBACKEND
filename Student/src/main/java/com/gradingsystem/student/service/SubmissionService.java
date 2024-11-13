package com.gradingsystem.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingsystem.student.Repository.SubmissionRepository;
import com.gradingsystem.student.model.Submission;

@Service
public class SubmissionService {
	
	@Autowired
	private SubmissionRepository sr;
	
	public String submitAssignment(Submission s)
	{
		try{
			sr.save(s);
			return "Submitted Successfully";
		}
		catch(Exception E)
		{
			return E.getMessage();
		}
	}
	
	public Submission getSubmissionByAssignment(String id)
	{
		return sr.getSubmissionByAssignmentId(id);
	}
	
	public List<Submission> getSubmissionByStudentId(String id)
	{
		return sr.getSubmissionByStudentId(id);
	}
}
