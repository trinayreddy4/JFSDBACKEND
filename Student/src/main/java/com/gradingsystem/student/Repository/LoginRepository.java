package com.gradingsystem.student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gradingsystem.student.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByUsernameAndPassword(String username, String password); // Custom query method
}
