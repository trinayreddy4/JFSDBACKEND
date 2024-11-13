package com.gradingsystem.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingsystem.admin.DTO.StudentDTO;
import com.gradingsystem.admin.model.Assignment;
import com.gradingsystem.admin.model.Student;
import com.gradingsystem.admin.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository sr;
	
	private Student convertToStudent(StudentDTO  s)
	{
		return new Student(s.getName(),s.getGender(),s.getEmail(),s.getMobileNo(),s.getLocation(),new ArrayList<Assignment>());
	}
	
	//Admin Should be able to Fetch the List of students
	//Admin Should be able to see the student's Submission
	
	public List<Student> getStudents()
	{
		return sr.findAll();
	}
	
	public String addStudent(StudentDTO S)
	{
		Student s = convertToStudent(S);
		sr.save(s);
		
		return "Student Added Successfully";
	}
	
	public List<Assignment> getStudentAssignment(int id)
	{
		Optional<Student> s = sr.findById(id);

		if (s.isPresent()) {  
		    Student temp = s.get(); 
		    return temp.getAssignments();  
		} else {
		    return null;  
		}

	}
	
}
