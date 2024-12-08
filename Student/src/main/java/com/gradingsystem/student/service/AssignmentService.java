package com.gradingsystem.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingsystem.student.Repository.AssignmentRepository;
import com.gradingsystem.student.model.Assignment;

@Service
public class AssignmentService {
	
	@Autowired
	private AssignmentRepository ar;
	
	public List<Assignment> getAllAssignments()
	{
		return ar.findAll();
	}
	
	public Assignment getAssignment(String id)
	{
		var a=  ar.findById(id);
		
		if(a.isPresent())
		{
			return a.get();
		}
		else
		{
			return null;
		}
	}
	
	public List<Assignment> getAllAssignmentsByFacultyId(int facultyId) {
	    return ar.findByFacultyId(facultyId);
	}

	
	
}
