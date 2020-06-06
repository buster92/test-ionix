package com.andresgarrido.testionix.model.jsonplaceholder;

public class UserRequest {
	private final String name;
	private final String email;
	private final String phone;

	public UserRequest(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
}
