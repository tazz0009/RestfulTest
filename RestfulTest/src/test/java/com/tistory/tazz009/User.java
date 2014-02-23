package com.tistory.tazz009;

public class User {

	private String id;
	private String password;
	
	public User() {
	}

	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean matchPassword(String password2) {
		if (password.equals(password2)) {
			return true;
		} else {
			return false;
		}
	}
	
}