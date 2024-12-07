package com.gradingsystem.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gradingsystem.admin.DTO.AssignmentDTO;
import com.gradingsystem.admin.DTO.AuthDTO;
import com.gradingsystem.admin.DTO.LoginDTO;
import com.gradingsystem.admin.DTO.StudentDTO;
import com.gradingsystem.admin.DTO.SubmissionDTO;
import com.gradingsystem.admin.model.Assignment;
import com.gradingsystem.admin.model.Student;
import com.gradingsystem.admin.model.Submission;
import com.gradingsystem.admin.service.AssignmentService;
import com.gradingsystem.admin.service.AuthService;
import com.gradingsystem.admin.service.StudentService;
import com.gradingsystem.admin.service.SubmissionService;

import jakarta.ws.rs.PathParam;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private StudentService sr;
	
	@Autowired
	private AssignmentService as;
	
	@Autowired
	private SubmissionService ss;
	
	@Autowired
	private AuthService aus;
	
	@GetMapping("/")
	public String hello()
	{
		return "<h1>Hello Trinay Reddy</h1>";
		
	}
	
	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/getStudents")
	public List<Student> getStudents() {
		return sr.getStudents();
	}
	
	@PostMapping("/addStudent")
	public String addStudent(@RequestBody StudentDTO s)
	{
		return sr.addStudent(s);
	}
	
	@GetMapping("/getStudentCount")
	public int getStudentsCount()
	{
		return sr.getStudentCount();
	}
	
	@PostMapping("/createAssignment")
	public String createAssignment(@ModelAttribute AssignmentDTO a,@RequestParam("file") MultipartFile file)
	{
		return as.createAssignment(a, file);
	}
	
	@CrossOrigin(origins = "http://localhost:5173")
	@DeleteMapping("/deleteStudent")
	public String deleteStudent(@PathParam("id") int id)
	{
		return sr.deleteStudent(id);
	}
	
	@GetMapping("/getAssignments")
	public List<Assignment> getAssignments()
	{
		return as.getAllAssignments();
	}
	
	@GetMapping("/getAssignmentCount")
	public int getAssignmentCount()
	{
		return as.getAllAssignments().size();
	}
	
	
	@GetMapping("/getAllSubmissions")
	public List<Submission> getAllSubmissions()
	{
		return ss.getAllSubmissions();
	}
	
	@GetMapping("/getSubmission")
	public Submission getSubmissionById(@PathParam("id") String id)
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
	
	@PutMapping("/postAssignmentMarks")
	public String postAssignmentMark(@RequestBody SubmissionDTO sdto , @PathParam("id") String id)
	{
		return ss.postAssignmentMark(sdto,id);
	}
	
	@PutMapping("/updateStudent")
	public String updateStudent(@RequestBody StudentDTO s,@PathParam("id") int id)
	{
		return sr.updateStudent(s,id);
	}
	
	@GetMapping("/getSubmissionsPendingCount")
	public int getSubmissionsPendingCount()
	{
		return ss.getSubmissionsPendingCount();
	}
	
	
	@GetMapping("/getStudentSubmissions/{student_id}")
	public List<Submission> getStudentSubmissions(@PathVariable("student_id") int student_id)
	{
		return ss.getSubmissionByStudentId(student_id);
	}
	
	@GetMapping("/login")
	public LoginDTO login(@RequestBody AuthDTO a)
	{
		System.out.println(a.getUname());
		System.out.println(a.getPassword());
		
		LoginDTO ret=aus.login(a);
		if(ret!=null) {
		System.out.println(ret.getUsername());
		System.out.println(ret.getRole());}
		return ret;
	}
}
