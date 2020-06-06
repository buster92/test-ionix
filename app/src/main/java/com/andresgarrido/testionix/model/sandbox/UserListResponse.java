package com.andresgarrido.testionix.model.sandbox;

import java.util.List;

public class UserListResponse {
	private int responseCode;
	private String description;
	private UserList userList;

	public UserListResponse(int responseCode, String description, List<UserResponse> userList) {
		this.responseCode = responseCode;
		this.description = description;
		this.userList = new UserList(userList);
	}

	public static class UserList {
		public List<UserResponse> items;

		public UserList(List<UserResponse> items) {
			this.items = items;
		}
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
		return userList.items;
	}

	public void setUserList(List<UserResponse> userList) {
		this.userList.items.clear();
		this.userList.items.addAll(userList);
	}
}
