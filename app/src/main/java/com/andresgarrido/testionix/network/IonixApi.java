package com.andresgarrido.testionix.network;

import com.andresgarrido.testionix.event.SearchUserOkEvent;
import com.andresgarrido.testionix.event.UserCreatedErrorEvent;
import com.andresgarrido.testionix.event.UserCreatedOkEvent;
import com.andresgarrido.testionix.model.jsonplaceholder.UserRequest;
import com.andresgarrido.testionix.model.jsonplaceholder.UserResponse;
import com.andresgarrido.testionix.model.sandbox.UserListResponse;
import com.andresgarrido.testionix.util.Utils;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IonixApi {

	private static SandboxApi getSandboxEndpoint() {
		return ApiClient.getSandboxClient().create(SandboxApi.class);
	}

	private static JsonPlaceholderApi getJsonPlaceholderEndpoint() {
		return ApiClient.getJsonPlaceholderClient().create(JsonPlaceholderApi.class);
	}

	public static void searchRut(String rut) {
		String rutEncoded = Utils.encodeString(rut);
		getSandboxEndpoint().search(rutEncoded).enqueue(new Callback<UserListResponse>() {
			@Override
			public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
				if (response.isSuccessful() && response.body() != null) {
					EventBus.getDefault().post(new SearchUserOkEvent(response.body().getUserList()));
				}
			}

			@Override
			public void onFailure(Call<UserListResponse> call, Throwable t) {

			}
		});
	}

	public static void createUser(UserRequest userRequest) {
		getJsonPlaceholderEndpoint().createUser(userRequest).enqueue(new Callback<UserResponse>() {
			@Override
			public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
				if (response.isSuccessful() && response.body() != null) {
					EventBus.getDefault().post(new UserCreatedOkEvent(response.body()));
					return;
				}

				EventBus.getDefault().post(new UserCreatedErrorEvent());
			}

			@Override
			public void onFailure(Call<UserResponse> call, Throwable t) {
				t.printStackTrace();
				EventBus.getDefault().post(new UserCreatedErrorEvent());
			}
		});

	}
}
