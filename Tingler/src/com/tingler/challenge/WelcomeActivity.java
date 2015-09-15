package com.tingler.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tingler.challenge.api.call.Authentication;

public class WelcomeActivity extends Activity {
	TextView toolbar_title;
	Button btn_fb, btn_google, btn_signup, btn_login;
	public static EditText etxt_mobile_signup, etxt_mobile_login, etxt_pass;
	Authentication authentication;
	private static int FACBOOK_TAG = 2;
	private static int GOOGLE_LOGIN_TAG = 0;
	private static int Login_TAG = 5;
	private static int Signup_TAG = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_welcome);
		init();
	}

	public void checkLogin() {

	}

	public void init() {
		authentication = new Authentication(this);
		toolbar_title = (TextView) findViewById(R.id.toolbar_title);
		toolbar_title.setText("Welcome");
		btn_fb = (Button) findViewById(R.id.btn_fb);
		btn_google = (Button) findViewById(R.id.btn_google);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_signup = (Button) findViewById(R.id.btn_signup);
		//btn_forgot = (Button) findViewById(R.id.btn_forgot);
		etxt_mobile_signup = (EditText) findViewById(R.id.etxt_mobile_signup);
		etxt_mobile_login = (EditText) findViewById(R.id.etxt_mobile_login);
		etxt_pass = (EditText) findViewById(R.id.etxt_pass);

		btn_signup.setOnClickListener(authentication
				.loginAuthentication(Signup_TAG));
		btn_login.setOnClickListener(authentication
				.loginAuthentication(Login_TAG));
		
		btn_fb.setOnClickListener(authentication.loginAuthentication(FACBOOK_TAG));
		btn_google.setOnClickListener(authentication.loginAuthentication(GOOGLE_LOGIN_TAG));
		
	}
	@Override
	protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, responseCode, intent);
		if (Authentication.activityResult == FACBOOK_TAG) {
			/*Session.getActiveSession().onActivityResult(this, requestCode,
					responseCode, intent);
			authentication.facebooklogin.callingFBResult(this);*/
		} else if (Authentication.activityResult == GOOGLE_LOGIN_TAG) {
			authentication.onActivityResult(requestCode, responseCode, intent);
		}
	}
}
