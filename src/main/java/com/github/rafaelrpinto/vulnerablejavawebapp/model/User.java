package com.github.rafaelrpinto.vulnerablejavawebapp.model;

import java.io.Serializable;

/**
 * 
 * Registered user of the application.
 * 
 * @author Rafael
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 5746803907489367890L;

	private int id;
	private String name;
	private String email;
	private String password;

	public User() {

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
