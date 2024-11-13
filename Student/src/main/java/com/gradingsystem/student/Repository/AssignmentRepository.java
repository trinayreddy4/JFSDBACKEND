package com.gradingsystem.student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradingsystem.student.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, String>{

}
