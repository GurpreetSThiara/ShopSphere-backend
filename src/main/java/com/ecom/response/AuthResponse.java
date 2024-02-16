package com.ecom.response;

public class AuthResponse {
	
	private String jwt;
	
	private boolean status;

	private String email;
	
	public AuthResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AuthResponse(String jwt, boolean status , String email) {
		super();
		this.jwt = jwt;
		this.status = status;
		this.email = email;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
