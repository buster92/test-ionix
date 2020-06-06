package com.andresgarrido.testionix.model;

import com.google.gson.annotations.SerializedName;

public class UserDetail {
	private String email;

	@SerializedName("phone_number")
	private String phoneNumber;

	public UserDetail(String email, String phoneNumber) {
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
