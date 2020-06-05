package com.andresgarrido.testionix;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
	}
}
