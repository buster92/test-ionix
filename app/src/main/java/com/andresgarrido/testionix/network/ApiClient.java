package com.andresgarrido.testionix.network;

import androidx.annotation.NonNull;

import com.andresgarrido.testionix.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

class ApiClient {


	private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
			.connectTimeout(60, TimeUnit.SECONDS)
			.readTimeout(60, TimeUnit.SECONDS)
			.writeTimeout(60, TimeUnit.SECONDS);

	private static Retrofit sandboxClient;
	private static Retrofit jsonPlaceholderClient;

	@NonNull
	static Retrofit getSandboxClient() {
		if (sandboxClient == null) {

			try {
				sandboxClient = new Retrofit.Builder()
						.baseUrl(BuildConfig.SANDBOX_URL)
						.client(httpClient.build())
						.build();

			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

		return sandboxClient;
	}

	@NonNull
	static Retrofit getJsonPlaceholderClient() {
		if (jsonPlaceholderClient == null) {

			try {
				jsonPlaceholderClient = new Retrofit.Builder()
						.baseUrl(BuildConfig.JSON_PLACEHOLDER_URL)
						.client(httpClient.build())
						.build();

			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

		return jsonPlaceholderClient;
	}
}


