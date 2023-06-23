package com.cognizant.MovieBookingApp.Model;

import java.util.ArrayList;

public class SecurityRequest {

	private String token;

	private ArrayList<String> role;

	public SecurityRequest() {
	}

	public SecurityRequest(String token, ArrayList<String> role) {
		super();
		this.token = token;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ArrayList<String> getRole() {
		return role;
	}

	public void setRole(ArrayList<String> role) {
		this.role = role;
	}
	
	

}
