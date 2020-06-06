package com.andresgarrido.testionix.model.sandbox;

import com.andresgarrido.testionix.model.jsonplaceholder.UserRequest;

public class UserResponse {
	public String name;
	public UserDetail detail;

	public UserResponse(String name, UserDetail detail) {
		this.name = name;
		this.detail = detail;
	}

	public UserResponse(String name, String email, String phoneNumber) {
		this.name = name;
		this.detail = new UserDetail(email, phoneNumber);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDetail getDetail() {
		return detail;
	}

	public void setDetail(UserDetail detail) {
		this.detail = detail;
	}

	public UserRequest getUserRequest() {
		return new UserRequest(name, detail.getEmail(), detail.getPhoneNumber());
	}
}
