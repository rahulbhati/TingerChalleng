package com.tingler.challenge;

import com.tingler.challenge.api.call.Authentication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends Activity{

	Authentication authentication;
	Button btn_account_active;
	private static int ACTIVE_ACCOUNT =6;
	TextView toolbar_title;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_profile);
	init();
}
public void init() {
	authentication = new Authentication(this);
	toolbar_title = (TextView) findViewById(R.id.toolbar_title);
	toolbar_title.setText("Active Account");
//	btn_account_active = (Button) findViewById(R.id.btn_account_active);
//	btn_account_active.setOnClickListener(authentication.loginAuthentication(ACTIVE_ACCOUNT));
	
}
}
