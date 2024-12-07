package com.gradingsystem.admin.DTO;

public class AuthDTO {
	
	String username;
	String Password;
	public AuthDTO() {
		super();
	}
	public String getUname() {
		return username;
	}
	public AuthDTO(String username, String password) {
		super();
		this.username = username;
		Password = password;
	}
	public void setUname(String uname) {
		username = uname;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
}
