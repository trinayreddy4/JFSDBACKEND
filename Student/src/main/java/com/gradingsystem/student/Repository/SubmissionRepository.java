package com.gradingsystem.student.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gradingsystem.student.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String> {

    // Query to find a submission by the assignmentId
    @Query("SELECT s FROM Submission s WHERE s.AssignmentId = :id")
    public Submission getSubmissionByAssignmentId(String id);
    
    // Query to find submissions by the studentId
    @Query("SELECT s FROM Submission s WHERE s.student_id = :id")
    public List<Submission> getSubmissionByStudentId(String id);
}
