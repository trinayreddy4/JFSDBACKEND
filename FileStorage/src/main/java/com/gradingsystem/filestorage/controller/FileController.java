package com.gradingsystem.filestorage.controller;

import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.gradingsystem.filestorage.service.FileService;

import jakarta.ws.rs.PathParam;

@RestController
@RequestMapping("file")
public class FileController {
	
	@Autowired
	private FileService fs;
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file)
	{
		return fs.uploadFile(file);
	}
	
	@GetMapping("/getFile")
	public ResponseEntity<Resource> downloadFile(@PathParam("name") String name)
	{
		var file = fs.downloadFile(name);
		
		try {
			HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        
			return ResponseEntity.ok().contentLength(file.length()).contentType(MediaType.APPLICATION_OCTET_STREAM)
					.headers(headers)
					.body(new InputStreamResource(Files.newInputStream(file.toPath())));
		}
		catch(Exception e)
		{
			return ResponseEntity.notFound().build();
		}
	}
}
