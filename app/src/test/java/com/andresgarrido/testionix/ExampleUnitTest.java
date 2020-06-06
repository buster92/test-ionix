package com.andresgarrido.testionix;

import com.andresgarrido.testionix.model.sandbox.UserListResponse;
import com.andresgarrido.testionix.model.sandbox.UserResponse;
import com.andresgarrido.testionix.network.SandboxApi;
import com.andresgarrido.testionix.util.Utils;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() {
		assertEquals(4, 2 + 2);
	}

	@Test
	public void testEncodeString() {
		String rut = "1-9";
		assertEquals("FyaSTkGi8So=", Utils.encodeString(rut));
	}

	@Test
	public void testSearchUsers() {
		SandboxApi mockedApiInterface = Mockito.mock(SandboxApi.class);
		final Call<UserListResponse> mockedCall = Mockito.mock(Call.class);

		String rut = Utils.encodeString("1-9");
		Mockito.when(mockedApiInterface.search(rut)).thenReturn(mockedCall);

		Mockito.doAnswer(new Answer() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Callback<UserListResponse> callback = invocation.getArgument(0, Callback.class);

				List<UserResponse> userList = new ArrayList<>();
				userList.add(new UserResponse("John", "jdoe@gmail.com", "+130256897875"));
				userList.add(new UserResponse("Anna", "asmith@gmail.com", "+5689874521"));
				userList.add(new UserResponse("Peter", "jdoe@gmail.com", "+668978542365"));



				UserListResponse expected = new UserListResponse(0,"ok", userList);
				callback.onResponse(mockedCall, Response.success(expected));
				// or callback.onResponse(mockedCall, Response.error(404. ...);
				// or callback.onFailure(mockedCall, new IOException());

				return null;
			}
		}).when(mockedCall).enqueue(any(Callback.class));

	}
}