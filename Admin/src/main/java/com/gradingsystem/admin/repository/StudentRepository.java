package com.gradingsystem.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gradingsystem.admin.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
}
