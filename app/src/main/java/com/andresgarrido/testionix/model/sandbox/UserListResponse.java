package com.andresgarrido.testionix.model.sandbox;

import java.util.ArrayList;
import java.util.List;

public class UserListResponse {
	public int responseCode;
	public String description;
	public UserList result;

	public UserListResponse(int responseCode, String description, List<UserResponse> userList) {
		this.responseCode = responseCode;
		this.description = description;
		this.result = new UserList(userList);
	}


	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserResponse> getUserList() {
		if (result.items == null)
			return new ArrayList<>();
		return result.items;
	}

	public void setUserList(List<UserResponse> userList) {
		this.result.items.clear();
		this.result.items.addAll(userList);
	}
}
