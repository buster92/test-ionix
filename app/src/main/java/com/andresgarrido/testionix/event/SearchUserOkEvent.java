package com.andresgarrido.testionix.event;

import com.andresgarrido.testionix.model.sandbox.UserResponse;

import java.util.List;

public class SearchUserOkEvent {
	public final List<UserResponse> userList;

	public SearchUserOkEvent(List<UserResponse> userList) {
		this.userList = userList;
	}
}
