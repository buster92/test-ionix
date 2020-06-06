package com.andresgarrido.testionix.network;

public class IonixApi {

	private static SandboxApi getSandboxEndpoint() {
		return ApiClient.getSandboxClient().create(SandboxApi.class);
	}

	private static JsonPlaceholderApi getJsonPlaceholderEndpoint() {
		return ApiClient.getJsonPlaceholderClient().create(JsonPlaceholderApi.class);
	}

}
