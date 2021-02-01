package com.xml.projekat.dto;

import com.xml.projekat.model.TUser;

public class TUserDTO {
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String type;
	private String role;
	
	public TUserDTO(String username, String password, String email, String firstName, String lastName, String type) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = "GRADJANIN";
		this.role = "ROLE_USER";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public TUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TUserDTO(TUser user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.firstName =  user.getFirstName();
		this.lastName = user.getLastName();
		this.type =  user.getTitle();
		this.role =  user.getRole();
	}
	

}
