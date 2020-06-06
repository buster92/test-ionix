package com.andresgarrido.testionix.network;

import com.andresgarrido.testionix.model.UserListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SandboxApi {

	@GET("search")
	Call<UserListResponse> search(@Query("rut") String rut);
}
