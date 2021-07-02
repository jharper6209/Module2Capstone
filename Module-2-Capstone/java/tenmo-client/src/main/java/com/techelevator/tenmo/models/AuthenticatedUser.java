package com.techelevator.tenmo.models;

public class AuthenticatedUser {
	
	private String token;
	private AppUser user;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public AppUser getUser() {
		return user;
	}
	public void setUser(AppUser user) {
		this.user = user;
	}
}
