package com.gradingsystem.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingsystem.admin.model.Faculty;
import com.gradingsystem.admin.repository.FacultyRepository;

@Service
public class FacultyService {
	
	@Autowired
	private FacultyRepository fr;
	
	public Faculty getFacultyDetails(int id)
	{
		Optional<Faculty> fa = fr.findById(id); 
		if(fa.isPresent())
		{
			return fa.get();
		}
		return null;
	}
	public List<Faculty> getAllFaculties()
	{
		return fr.findAll();
	}
	public int getFacultyCount()
	{
		return fr.findAll().size();
	}
		    // Add Faculty
	    public String addFaculty(Faculty faculty) {
	        fr.save(faculty);
	        return "Faculty added successfully";
	    }

	    // Update Faculty
	    public String updateFaculty(int id, Faculty updatedFaculty) {
	        Optional<Faculty> optionalFaculty = fr.findById(id);
	        if (optionalFaculty.isPresent()) {
	            Faculty existingFaculty = optionalFaculty.get();
	            existingFaculty.setName(updatedFaculty.getName());
	            existingFaculty.setEmail(updatedFaculty.getEmail());
	            existingFaculty.setAddress(updatedFaculty.getAddress());
	            fr.save(existingFaculty);
	            return "Faculty updated successfully";
	        }
	        return "Faculty not found with id: " + id;
	    }

	    // Delete Faculty
	    public String deleteFaculty(int id) {
	        Optional<Faculty> optionalFaculty = fr.findById(id);
	        if (optionalFaculty.isPresent()) {
	            fr.deleteById(id);
	            return "Faculty deleted successfully";
	        }
	        return "Faculty not found with id: " + id;
	    }


}
