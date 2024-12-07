package com.gradingsystem.admin.DTO;

public class LoginDTO {
	
	public LoginDTO(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}
	private String username;
	private String role;
	private int id;
	public int getId() {
		return id;
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
