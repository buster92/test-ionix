package com.andresgarrido.testionix;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.andresgarrido.testionix.databinding.ActivityMainBinding;
import com.andresgarrido.testionix.event.SearchUserErrorEvent;
import com.andresgarrido.testionix.event.SearchUserOkEvent;
import com.andresgarrido.testionix.event.UserCreatedErrorEvent;
import com.andresgarrido.testionix.event.UserCreatedOkEvent;
import com.andresgarrido.testionix.model.jsonplaceholder.UserRequest;
import com.andresgarrido.testionix.model.sandbox.UserResponse;
import com.andresgarrido.testionix.network.IonixApi;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;
	private UserRequest userRequest;

	private EditText rutEditText;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		View view = binding.getRoot();
		setContentView(view);

		Account[] accounts = AccountManager.get(this).getAccounts();
		if(accounts != null && accounts.length > 0) {
			ArrayList playAccounts = new ArrayList();
			for (Account account : accounts) {
				String name = account.name;
				String type = account.type;
				if(account.type.equals("com.google")) {
					playAccounts.add(account.name);
				}
				Log.d("TAG", "Account Info: " + name + ":" + type);
			}
			Log.d("tag", "Google Play Accounts present on phone are :: " + playAccounts);
		}
		initViews();
	}

	@Override
	protected void onResume() {
		super.onResume();
		EventBus.getDefault().register(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		EventBus.getDefault().unregister(this);
	}

	private void initViews() {
		binding.mainButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				requestUserRut();
			}
		});

		binding.mainButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createUser();
			}
		});
	}

	private void requestUserRut() {

		final AlertDialog dialog = new AlertDialog.Builder(this)
				.setView(R.layout.dialog_user_rut)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						String rut = rutEditText.getText().toString();
						if (TextUtils.isEmpty(rut)) {
							rutEditText.setError(getString(R.string.error_field_empty));
						} else {
							IonixApi.searchRut(rut);
							showLoading();
						}
					}
				})
				.setNegativeButton(android.R.string.cancel, null)
				.setCancelable(false)
				.create();
		dialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialogInterface) {
				rutEditText = dialog.findViewById(R.id.rut_edit_text);
			}
		});
		dialog.show();
	}

	private void createUser() {
		if (userRequest == null) {
			Toast.makeText(this, R.string.error_user_dont_exist, Toast.LENGTH_LONG).show();
			return;
		}
		IonixApi.createUser(userRequest);
		showLoading();
	}


	public void showLoading() {
		hideLoading();

		progress = new ProgressDialog(this);
		progress.setMessage(getString(R.string.loading));
		progress.show();
		progress.setCancelable(false);
		progress.setCanceledOnTouchOutside(false);
	}

	public void hideLoading() {
		if (progress != null) {
			progress.dismiss();
			progress = null;
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onSearchUserOk(SearchUserOkEvent event) {
		hideLoading();
		//we only want the second user so we validate that exists
		if (event.userList.size() < 2) {
			Toast.makeText(this, R.string.error_generic, Toast.LENGTH_SHORT).show();
			return;
		}
		UserResponse userResponse = event.userList.get(1);
		userRequest = userResponse.getUserRequest();
		Toast.makeText(this, R.string.user_registered, Toast.LENGTH_SHORT).show();
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onSearchUserError(SearchUserErrorEvent event) {
		hideLoading();
		Toast.makeText(this, R.string.error_generic, Toast.LENGTH_SHORT).show();
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onUserCreatedOk(UserCreatedOkEvent event) {
		hideLoading();
		String id = event.user.getId();
		new AlertDialog.Builder(this)
				.setMessage(getString(R.string.user_created, id))
				.setPositiveButton(android.R.string.ok, null)
				.show();
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onUserCreatedError(UserCreatedErrorEvent event) {
		hideLoading();
		Toast.makeText(this, R.string.error_generic, Toast.LENGTH_SHORT).show();
	}

}
