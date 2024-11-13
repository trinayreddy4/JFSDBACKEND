package com.gradingsystem.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gradingsystem.admin.DTO.AssignmentDTO;
import com.gradingsystem.admin.DTO.StudentDTO;
import com.gradingsystem.admin.DTO.SubmissionDTO;
import com.gradingsystem.admin.model.Assignment;
import com.gradingsystem.admin.model.Student;
import com.gradingsystem.admin.model.Submission;
import com.gradingsystem.admin.service.AssignmentService;
import com.gradingsystem.admin.service.StudentService;
import com.gradingsystem.admin.service.SubmissionService;

import jakarta.ws.rs.PathParam;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private StudentService sr;
	
	@Autowired
	private AssignmentService as;
	
	@Autowired
	private SubmissionService ss;
	
	@GetMapping("/")
	public String hello()
	{
		return "Hello Trinay Reddy";
		
	}
	
	@GetMapping("/getStudents")
	public List<Student> getStudents() {
		return sr.getStudents();
	}
	
	@PostMapping("/addStudent")
	public String addStudent(@RequestBody StudentDTO s)
	{
		return sr.addStudent(s);
	}
	
	@PostMapping("/createAssignment")
	public String createAssignment(@ModelAttribute AssignmentDTO a,@RequestParam("file") MultipartFile file)
	{
		return as.createAssignment(a, file);
	}
	
	@GetMapping("/getAssignments")
	public List<Assignment> getAssignments()
	{
		return as.getAllAssignments();
	}
	
	@GetMapping("/getAllSubmissions")
	public List<Submission> getAllSubmissions()
	{
		return ss.getAllSubmissions();
	}
	
	public SubmissionDTO getSubmissionById(@RequestBody String id)
	{
		return ss.getSubmissionDetails(id);
	}
	
	@GetMapping("/getSubmissionFile")
	public ResponseEntity<Resource> getSubmissionFileById(@PathParam("id") String id)
	{

//		System.out.println("hello");
//		id = File.separator+id;
//		System.out.println(id);
		return ss.getSubmissionFileById(id);
	}
	
	//Assignment id
	@GetMapping("/getAssignmentFile")
	public ResponseEntity<Resource> getAssignmentFileById(@PathParam("id") String id)
	{
		return as.getAssignmentFileById(id);
	}
	
	//@Submission Id
	
	@PutMapping("/postAssignmentMarking")
	public String postAssignmentMark(@RequestBody SubmissionDTO sdto , @PathParam("id") String id)
	{
		return ss.postAssignmentMark(sdto,id);
	}
	
	
}
