package com.gradingsystem.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingsystem.admin.DTO.StudentDTO;
import com.gradingsystem.admin.model.Assignment;
import com.gradingsystem.admin.model.Login;
import com.gradingsystem.admin.model.Student;
import com.gradingsystem.admin.repository.LoginRepository;
import com.gradingsystem.admin.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository sr;
	
	@Autowired
	private LoginRepository lr;
	
	private Student convertToStudent(StudentDTO  s)
	{
		return new Student(s.getName(),s.getGender(),s.getEmail(),s.getMobileNo(),s.getLocation(),s.getFaculty_id());
	}
	
	//Admin Should be able to Fetch the List of students
	//Admin Should be able to see the student's Submission
	
	public List<Student> getStudents()
	{
		return sr.findAll();
	}
	public List<Student> getStudents(int id)
	{
		return sr.findAllByFacultyId(id);
	}
	public String addStudent(StudentDTO S)
	{
		Student s = convertToStudent(S);
		System.out.println(s);
		s=sr.save(s);
		
		Login l = new Login();
		
		l.setUsername(s.getEmail());
		l.setPassword(s.getEmail());
		l.setRole("STUDENT");
		
		
		return "Student Added Successfully";
	}
	
	public String deleteStudent(int id)
	{
		try {
			sr.deleteById(id);
			return "Student Deleted SuccessFully";
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
	public String updateStudent(StudentDTO s,int id)
	{
		try {
			@SuppressWarnings("deprecation")
			var se = sr.getById(id);
			
			
			se.setEmail(s.getEmail());
			se.setGender(s.getGender());
			se.setLocation(s.getLocation());
			se.setMobileNo(s.getMobileNo());
			se.setName(s.getName());
			
			sr.save(se);
			return "updated Successfully";
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
	public int getStudentCount()
	{
		return sr.getStudentCount();
	}
	
	public int getStudentCount(int id)
	{
		return sr.findAllByFacultyId(id).size();
	}
	
	
}
