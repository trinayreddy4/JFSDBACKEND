package com.gradingsystem.student.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.gradingsystem.student.DTO.AuthDTO;
import com.gradingsystem.student.DTO.LoginDTO;
import com.gradingsystem.student.DTO.SubmissionDTO;
import com.gradingsystem.student.model.Assignment;
import com.gradingsystem.student.model.Submission;
import com.gradingsystem.student.service.AssignmentService;
import com.gradingsystem.student.service.AuthService;
import com.gradingsystem.student.service.SubmissionService;

import jakarta.ws.rs.PathParam;

@CrossOrigin
@RestController
@RequestMapping("student")
public class StudentController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SubmissionService ss;
	
	@Autowired
	private AssignmentService as;
	
	@Autowired
	private AuthService aus;
	
	@PostMapping("/submit")
	public String submitAssignment(@ModelAttribute SubmissionDTO sd,MultipartFile file)
	{
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource()); 
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:2000/file/upload", 
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        String path= response.getBody();
        
        Submission s  =new Submission(sd.getId(),path,sd.getAssignmentId(),sd.getSubmittedOn());
        
        return ss.submitAssignment(s);
	}
	
	@GetMapping("/getAssignments")
	public List<Assignment> getAllAssignments(@PathParam("id") int id)
	{
		return as.getAllAssignmentsByFacultyId(id);
	}
	
	@GetMapping("/getAssignmentsCnt")
	public int getAllAssignmentsCnt(@PathParam("id") int id)
	{
		return as.getAllAssignmentsByFacultyId(id).size();
	}
	
	//Find The particular Submission Assignment Detail  by assignment id uuid
	@GetMapping("/getAssignment")
	public Assignment getAssignment(@PathParam("id") String id)
	{
		return as.getAssignment(id);
	}
	
	
	@GetMapping("/getAssignmentSubmission")
	public Submission getSubmissionByAssignment(@PathParam("id") String id)
	{
		return ss.getSubmissionByAssignment(id);
	}
	
	@GetMapping("/getSubmissions")
	public List<Submission> getAllSubmissions(@PathParam("id") String id)
	{
		return ss.getSubmissionByStudentId(id);
	}
	
	@PostMapping("/login")
	public AuthDTO login(@RequestBody LoginDTO l)
	{
		return aus.login(l.getUsername(),l.getPassword());
	}
}
