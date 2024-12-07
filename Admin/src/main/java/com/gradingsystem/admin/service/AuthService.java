package com.gradingsystem.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingsystem.admin.DTO.AuthDTO;
import com.gradingsystem.admin.DTO.LoginDTO;
import com.gradingsystem.admin.model.Login;
import com.gradingsystem.admin.repository.LoginRepository;

@Service
public class AuthService {
	
	@Autowired
	private LoginRepository lr;
	
	public LoginDTO login(AuthDTO d)
	{
		if(lr.login(d.getUname(),d.getPassword()) > 0) {
			Optional<Login> l = lr.findById(d.getUname());
			
			if(l.isPresent())
			{
				return new LoginDTO(l.get().getUsername(),l.get().getRole(),l.get().getId());
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
}
