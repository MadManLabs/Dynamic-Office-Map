package com.fsociety.domclient.core;

public class Settings {
	private Integer id;
	private String username;

	public Settings() {
		this.id = 0;
		this.username = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
