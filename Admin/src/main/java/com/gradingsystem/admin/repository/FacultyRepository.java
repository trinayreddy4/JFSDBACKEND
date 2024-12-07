package com.gradingsystem.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gradingsystem.admin.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {

}
