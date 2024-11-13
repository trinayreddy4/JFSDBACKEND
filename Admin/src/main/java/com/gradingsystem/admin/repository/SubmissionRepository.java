package com.gradingsystem.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gradingsystem.admin.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String> {

}
