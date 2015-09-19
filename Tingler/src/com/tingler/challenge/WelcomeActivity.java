package com.tingler.challenge;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.Profile;

public class WelcomeActivity extends Activity implements
		android.view.View.OnClickListener {
	TextView toolbar_title;
	Button btn_fb, btn_google, btn_signup, btn_login;
	public static EditText etxt_mobile_signup, etxt_mobile_login, etxt_pass;
	Authentication authentication;
	private static int FACBOOK_TAG = 2;
	private static int GOOGLE_LOGIN_TAG = 0;
	private static int Login_TAG = 5;
	private static int Signup_TAG = 1;
Profile profile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		checkLogin();
		setContentView(R.layout.activity_welcome);
		init();
	}

	public void checkLogin() {
        profile=new Profile(this);
        System.out.println("profiile id"+profile.getId());
        if(profile.getId().length()>0){ // check first time start app
        	if(Profile.getIsVarify().equalsIgnoreCase("1")){    // check if is_varity =1 then user account activated
        		if(Profile.getStatus().equalsIgnoreCase("1")){  // check if status =1 then user completed profile info 
        			Intent intent=new Intent(this,MainActivity.class);
        			startActivity(intent);
        			finish();
        		}else{
        			Intent intent=new Intent(this,ProfileActivity.class);
        			startActivity(intent);
        			finish();
        		}
        	}else{
        		Intent intent=new Intent(this,AccountActiveActivity.class);
    			startActivity(intent);
    			finish();
        	}
        }
	}

	public void init() {
		authentication = new Authentication(this);
		toolbar_title = (TextView) findViewById(R.id.toolbar_title);
		toolbar_title.setText("Welcome");
		btn_fb = (Button) findViewById(R.id.btn_fb);
		btn_google = (Button) findViewById(R.id.btn_google);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_signup = (Button) findViewById(R.id.btn_signup);
		// btn_forgot = (Button) findViewById(R.id.btn_forgot);
		etxt_mobile_signup = (EditText) findViewById(R.id.etxt_mobile_signup);
		etxt_mobile_login = (EditText) findViewById(R.id.etxt_mobile_login);
		etxt_pass = (EditText) findViewById(R.id.etxt_pass);

		btn_signup.setOnClickListener(this);
		btn_login.setOnClickListener(this);

		btn_fb.setOnClickListener(authentication
				.loginAuthentication(FACBOOK_TAG));
		btn_google.setOnClickListener(authentication
				.loginAuthentication(GOOGLE_LOGIN_TAG));

	}

	@Override
	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, responseCode, intent);
		if (Authentication.activityResult == FACBOOK_TAG) {
			/*
			 * Session.getActiveSession().onActivityResult(this, requestCode,
			 * responseCode, intent);
			 * authentication.facebooklogin.callingFBResult(this);
			 */
		} else if (Authentication.activityResult == GOOGLE_LOGIN_TAG) {
			authentication.onActivityResult(requestCode, responseCode, intent);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.btn_signup) {
			Map<String, String> params = new HashMap<String, String>();
			params.put(APIS.MOBILT, etxt_mobile_signup.getText().toString()
					.trim());
			authentication.requestSignupAPI(params);

		} else if (v.getId() == R.id.btn_login) {
			Map<String, String> params = new HashMap<String, String>();
			params.put(APIS.MOBILT, etxt_mobile_login.getText().toString()
					.trim());
			params.put(APIS.PASSWORD, etxt_pass.getText().toString().trim());
			authentication.requestLoginAPI(params);
		}
	}

}
