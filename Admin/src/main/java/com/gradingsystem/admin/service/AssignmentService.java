package com.gradingsystem.admin.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.gradingsystem.admin.DTO.AssignmentDTO;
import com.gradingsystem.admin.model.Assignment;
import com.gradingsystem.admin.repository.AssignmentRepository;


@Service
public class AssignmentService {
	
	@Autowired
	private AssignmentRepository ar;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public Assignment convertToAssignment(AssignmentDTO a,String path)
	{
		return new Assignment(a.getName(),a.getDescription(),path,a.getFaculty_id(),a.getDeadline(),a.getMaxMarks());
	}
	public String createAssignment(AssignmentDTO a, MultipartFile file) {
	    try {
	    	System.out.println(a.getFaculty_id());
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

	        String path = response.getBody();
	        
	        if (path == null || path.isEmpty()) {
	            return "File upload failed: Invalid file path.";
	        }

	        Assignment assignment = convertToAssignment(a, path);
	        ar.save(assignment);

	        return "Assignment Created Successfully";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "An error occurred: " + e.getMessage();
	    }
	}

	public Assignment getAssignmentById(String id)
	{
		var ass = ar.findById(id);
		if(ass.isPresent())
		{
			return ass.get();
		}
		return null;
	}
	
	public List<Assignment> getAllAssignments()
	{
		return ar.findAll();
	}
	
	public ResponseEntity<Resource> getAssignmentFileById(String id)
	{
		
	
		Optional<Assignment> sb = ar.findById(id);
		
		if(sb.isPresent())
		{
			Assignment s = sb.get();
			
			 var f = restTemplate.exchange(
					"http://localhost:2000/file/getFile?name="+s.getFilePath()
					,HttpMethod.GET
					,null,File.class
					);
			
			 File file = f.getBody();
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
	public String updateAssignment(Assignment updatedAssignment) {
        Optional<Assignment> existingAssignment = ar.findById(updatedAssignment.getId());
        if (existingAssignment.isPresent()) {
            Assignment assignment = existingAssignment.get();
            assignment.setName(updatedAssignment.getName());
            assignment.setDescription(updatedAssignment.getDescription());
            assignment.setDeadline(updatedAssignment.getDeadline());
            assignment.setMaxMarks(updatedAssignment.getMaxMarks());
            ar.save(assignment);
            return "Assignment updated successfully";
        } else {
            return "Assignment not found";
        }
    }
	
	public String deleteAssignment(String id) {
	    Optional<Assignment> assignment = ar.findById(id);
	    if (assignment.isPresent()) {
	        ar.deleteById(id);
	        return "Assignment deleted successfully";
	    } else {
	        return "Assignment not found";
	    }
	}
	
	public List<Assignment> getAssignmentsByFacultyId(int id)
	{
		return ar.getAssignmentsByFacultyId(id);
	}
}
