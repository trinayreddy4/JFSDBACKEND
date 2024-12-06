package com.gradingsystem.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gradingsystem.admin.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String> {
	
	@Query("SELECT COUNT(S) FROM Submission S WHERE S.marksAwarded = -1")
	public int getSubmissionsPendingCount();
	
	@Query("SELECT S FROM Submission S WHERE S.student_id=:student_id")
	public List<Submission> findSubmissionsByStudentId(int student_id);
}
