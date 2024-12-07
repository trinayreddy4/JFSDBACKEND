package com.gradingsystem.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gradingsystem.admin.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    
    @Query("SELECT COUNT(s) FROM Student s")
    public int getStudentCount();
    
    @Query("SELECT s FROM Student s WHERE s.faculty_id = :id")
    public List<Student> findAllByFacultyId(int id);
}
