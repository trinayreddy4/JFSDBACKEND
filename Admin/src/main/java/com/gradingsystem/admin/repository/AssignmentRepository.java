package com.gradingsystem.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gradingsystem.admin.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, String> {
	
}