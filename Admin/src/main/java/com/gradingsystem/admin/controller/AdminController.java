package com.gradingsystem.admin.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gradingsystem.admin.DTO.AssignmentDTO;
import com.gradingsystem.admin.DTO.AuthDTO;
import com.gradingsystem.admin.DTO.LoginDTO;
import com.gradingsystem.admin.DTO.StudentDTO;
import com.gradingsystem.admin.DTO.SubmissionDTO;
import com.gradingsystem.admin.model.Assignment;
import com.gradingsystem.admin.model.Faculty;
import com.gradingsystem.admin.model.Student;
import com.gradingsystem.admin.model.Submission;
import com.gradingsystem.admin.service.AssignmentService;
import com.gradingsystem.admin.service.AuthService;
import com.gradingsystem.admin.service.FacultyService;
import com.gradingsystem.admin.service.StudentService;
import com.gradingsystem.admin.service.SubmissionService;

import jakarta.ws.rs.PathParam;

@CrossOrigin
@RestController
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
	
	@Autowired
	private FacultyService fs;
	
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
	
	@GetMapping("/getStudent")
	public List<Student> getStudents(@PathParam("id") int id) {
		return sr.getStudents(id);
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
	
	@GetMapping("/getStudentCountr")
	public int getStudentsCount(@PathParam("id")int id)
	{
		return sr.getStudentCount(id);
	}
	
	
	@PostMapping("/createAssignment")
	public String createAssignment(@RequestParam("name") String name,
	                               @RequestParam("description") String description,
	                               @RequestParam("deadline") LocalDate deadline,
	                               @RequestParam("faculty_id")int id,
	                               @RequestParam("file") MultipartFile file) {
	    if (file.isEmpty()) {
	        throw new IllegalArgumentException("File is required");
	    }
	    AssignmentDTO ad = new AssignmentDTO();
	    ad.setDeadline(deadline);
	    ad.setDescription(description);
	    ad.setName(name);
	    ad.setFaculty_id(id);
	    if (ad.getDeadline() == null || ad.getDeadline().isBefore(LocalDate.now())) {
	        throw new IllegalArgumentException("Deadline must be today or in the future");
	    }

	    return as.createAssignment(ad, file);
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
	
	@GetMapping("/getAssignmentCountr")
	public int getAssignmentCount(@PathParam("id") int id)
	{
		return as.getAssignmentsByFacultyId(id).size();
	}
	
	@GetMapping("/getAssignmentsr")
	public List<Assignment> getAssignments(@PathParam("id") int id)
	{
		return as.getAssignmentsByFacultyId(id);
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
	
	@PutMapping("/updateAssignment")
    public String updateAssignment(@RequestBody Assignment assignment) {
        return as.updateAssignment(assignment);
    }
	
	@GetMapping("/getSubmissionsPendingCount")
	public int getSubmissionsPendingCount()
	{
		return ss.getSubmissionsPendingCount();
	}
	
	@DeleteMapping("/deleteAssignment/{id}")
	public String deleteAssignment(@PathVariable("id") String id) {
	    return as.deleteAssignment(id);
	}

	
	
	@GetMapping("/getStudentSubmissions/{student_id}")
	public List<Submission> getStudentSubmissions(@PathVariable("student_id") int student_id)
	{
		return ss.getSubmissionByStudentId(student_id);
	}
	
	@PostMapping("/login")
	public LoginDTO login(@RequestBody AuthDTO a)
	{
//		System.out.println(a.getUname());
//		System.out.println(a.getPassword());
		
		LoginDTO ret=aus.login(a);
		if(ret!=null) {
		System.out.println(ret.getUsername());
		System.out.println(ret.getRole());}
		return ret;
	}
	
	@GetMapping("/getFacultyDetails")
	public Faculty getFaculty(@PathParam("id") int id)
	{
		return fs.getFacultyDetails(id);
	}
	
	@GetMapping("/getFacultyCount")
	public int getFacultyCount()
	{
		return fs.getFacultyCount();
	}
	
	@GetMapping("/getAssignmentById")
	public Assignment getAssignmentById(@PathParam("id")String id)
	{
		return as.getAssignmentById(id);
	}
	
	@GetMapping("/getAllFaculties")
	public List<Faculty> getAllFaculties()
	{
		return fs.getAllFaculties();
	}
	
	@PostMapping("/addFaculty")
	public String addFaculty(@RequestBody Faculty faculty) {
	    return fs.addFaculty(faculty);
	}

	@PutMapping("/updateFaculty/{id}")
	public String updateFaculty(@RequestBody Faculty faculty, @PathVariable("id") int id) {
	    return fs.updateFaculty(id, faculty);
	}

	@DeleteMapping("/deleteFaculty/{id}")
	public String deleteFaculty(@PathVariable("id") int id) {
	    return fs.deleteFaculty(id);
	}

	
}
