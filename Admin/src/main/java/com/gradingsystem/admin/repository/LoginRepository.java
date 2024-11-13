package com.gradingsystem.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gradingsystem.admin.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

    @Query("SELECT COUNT(l) FROM Login l WHERE l.username = :uname AND l.password = :password")
    public int login(String uname, String password);
}
