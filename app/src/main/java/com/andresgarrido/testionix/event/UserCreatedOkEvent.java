package com.andresgarrido.testionix.event;

import com.andresgarrido.testionix.model.jsonplaceholder.UserResponse;

public class UserCreatedOkEvent {
	public final UserResponse user;

	public UserCreatedOkEvent(UserResponse user) {
		this.user = user;
	}
}
