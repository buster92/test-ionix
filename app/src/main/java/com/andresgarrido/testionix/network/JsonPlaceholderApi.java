package com.andresgarrido.testionix.network;

import com.andresgarrido.testionix.model.jsonplaceholder.*;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceholderApi {
	@POST("users")
	Call<UserResponse> createUser(@Body UserRequest body);
}
