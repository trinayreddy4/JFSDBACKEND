package com.gradingsystem.filestorage.service;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
@Service
public class FileService {
	public static String generateRandomFileName(String originalFileName) {
        
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String randomUUID = UUID.randomUUID().toString();
        return randomUUID + fileExtension;
    }
	public  String uploadFile(MultipartFile file)
	{
		if (file.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("File is empty");
			return "Invalid";
        }

        try {
//        	System.out.println(file.getOriginalFilename());
        	String name = generateRandomFileName(file.getOriginalFilename());
        	String path = System.getProperty("user.dir")+"/src/main/resources/uploads" +File.separator +name;
            File uploadedFile = new File(path);
            Files.copy(file.getInputStream(), uploadedFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
            return name;
//            return ResponseEntity.status(HttpStatus.SC_OK).body("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
        	return e.getMessage();
//            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
	}
	
	public File downloadFile(String name)
	{
		try {

        	String path = System.getProperty("user.dir")+"/src/main/resources/uploads" +File.separator +name;
            File uploadedFile = new File(path);
            return uploadedFile;
		
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
}
