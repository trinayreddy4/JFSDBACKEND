package com.gradingsystem.admin.DTO;

public class LoginDTO {
	
	
	private String username;
	private String role;
	private int id;
	private String password;
	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public LoginDTO() {
		super();
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LoginDTO(String username, String role, int id) {
		super();
		this.username = username;
		this.role = role;
		this.id = id;
	}
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
