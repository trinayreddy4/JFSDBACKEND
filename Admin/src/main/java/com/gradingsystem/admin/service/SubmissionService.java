package com.gradingsystem.admin.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gradingsystem.admin.DTO.SubmissionDTO;
import com.gradingsystem.admin.model.Submission;
import com.gradingsystem.admin.repository.SubmissionRepository;
import com.gradingsystem.admin.util.FileUtil;

@Service
public class SubmissionService {
	
	@Autowired
	private SubmissionRepository sr;
	
	public List<Submission> getAllSubmissions()
	{
		return sr.findAll();
	}
	
	public SubmissionDTO getSubmissionDetails(String id)
	{
		Optional<Submission> s = sr.findById(id);
		
		if(s.isPresent())
		{
			Submission sb = s.get();
			return new SubmissionDTO(sb.getStudent_id(),sb.getMarksAwarded(),sb.getAssignmentId(),sb.getSubmittedOn());
		}
		else
		return null;
	}
	
	public ResponseEntity<Resource> getSubmissionFileById(String id)
	{
		
	
		Optional<Submission> sb = sr.findById(id);
		
		if(sb.isPresent())
		{
			Submission s = sb.get();
			
			var file = FileUtil.downloadFile(s.getFilepath());
			
			try {
				return ResponseEntity.ok().contentLength(file.length()).contentType(MediaType.APPLICATION_OCTET_STREAM)
						.body(new InputStreamResource(Files.newInputStream(file.toPath()))) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.notFound().build();
			}
			
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	public String postAssignmentMark(SubmissionDTO sb,String id)
	{
		try {
			@SuppressWarnings("deprecation")
			Submission u = sr.getOne(id);
			
			u.setMarksAwarded(sb.getMarks());
			return "Marks Posted Successfully";
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
}
