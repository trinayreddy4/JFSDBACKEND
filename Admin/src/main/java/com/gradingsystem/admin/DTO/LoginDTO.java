package com.gradingsystem.admin.DTO;

public class LoginDTO {
	
	public LoginDTO(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}
	private String username;
	private String role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
