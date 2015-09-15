package com.tingler.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	TextView toolbar_title;
     Button btn_fb,btn_google,btn_signup,btn_login,btn_forgot;
     EditText etxt_mobile_signup,etxt_mobile_login,etxt_pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		checkLogin();
		setContentView(R.layout.activity_welcome);
		init();
	}

	public void checkLogin() {

	}

	public void init() {
		toolbar_title = (TextView) findViewById(R.id.toolbar_title);
		toolbar_title.setText("Welcome");
		btn_fb=(Button)findViewById(R.id.btn_fb);
		btn_google=(Button)findViewById(R.id.btn_google);
		btn_login=(Button)findViewById(R.id.btn_login);
		btn_signup=(Button)findViewById(R.id.btn_signup);
		btn_forgot=(Button)findViewById(R.id.btn_forgot);
		etxt_mobile_signup=(EditText)findViewById(R.id.etxt_mobile_signup);
		etxt_mobile_login=(EditText)findViewById(R.id.etxt_mobile_login);
		etxt_pass=(EditText)findViewById(R.id.etxt_pass);
	}
}
