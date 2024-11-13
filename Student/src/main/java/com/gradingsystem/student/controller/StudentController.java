package com.gradingsystem.student.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("student")
public class StudentController {
	
	public String submitAssignment(@ModelAttribute SubmissionDTO sd,@RequestParam("file") MultipartFile file)
	{
		
	}
}
