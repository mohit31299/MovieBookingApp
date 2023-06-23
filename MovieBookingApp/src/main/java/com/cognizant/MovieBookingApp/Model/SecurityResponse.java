package com.cognizant.MovieBookingApp.Model;

public class SecurityResponse {

	private boolean token;

	private boolean role;

	public SecurityResponse() {
	}

	public SecurityResponse(boolean token, boolean role) {
		super();
		this.token = token;
		this.role = role;
	}

	public boolean getToken() {
		return token;
	}

	public void setToken(boolean token) {
		this.token = token;
	}

	public boolean getRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}
	
	

}
