package com.gradingsystem.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gradingsystem.admin.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, String> {
	
	@Query("SELECT A FROM Assignment A WHERE A.faculty_id=:id")
	public List<Assignment> getAssignmentsByFacultyId(int id);
}
