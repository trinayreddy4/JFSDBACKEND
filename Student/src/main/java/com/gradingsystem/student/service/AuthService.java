package com.gradingsystem.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingsystem.student.DTO.AuthDTO;
import com.gradingsystem.student.DTO.LoginDTO;
import com.gradingsystem.student.Repository.LoginRepository;
import com.gradingsystem.student.model.Login;

@Service
public class AuthService {

    @Autowired
    private LoginRepository loginRepository;

    public AuthDTO login(String username, String password) {
        Login login = loginRepository.findByUsernameAndPassword(username, password);
        if (login != null) {
            // Assuming LoginDTO is used to pass necessary user details.
            AuthDTO temp = new AuthDTO();
            temp.setUsername(username);
            temp.setId(login.getId());
            temp.setRole("STUDENT");
            return temp;
        } else {
            return null; // Return null or throw an exception for invalid credentials.
        }
    }
}
