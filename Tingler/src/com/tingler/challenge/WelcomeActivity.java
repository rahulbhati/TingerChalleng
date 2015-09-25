package com.tingler.challenge;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.Session;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.api.call.FacebookLogin;
import com.tingler.challenge.util.Profile;
import com.tingler.challenge.util.Validations;

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
	
	public boolean facebookCall=false;
	public static FacebookLogin facebooklogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		checkLogin();
		setContentView(R.layout.activity_welcome);
		pkghash();
		init();
		
	}

	public void checkLogin() {
		profile = new Profile(this);
		System.out.println("profiile id" + profile.getId());
		if (profile.getId().length() > 0) { // check first time start app
			if (Profile.getIsVarify().equalsIgnoreCase("1")) { // check if
																// is_varity =1
																// then user
																// account
																// activated
				if (Profile.getStatus().equalsIgnoreCase("1")) { // check if
																	// status =1
																	// then user
																	// completed
																	// profile
																	// info
					Intent intent = new Intent(this, MainActivity.class);
					startActivity(intent);
					finish();
				} else {
					Intent intent = new Intent(this, ProfileActivity.class);
					startActivity(intent);
					finish();
				}
			} else {
				Intent intent = new Intent(this, AccountActiveActivity.class);
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

		/*btn_fb.setOnClickListener(authentication
				.loginAuthentication(FACBOOK_TAG));*/
		btn_fb.setOnClickListener(this);
		btn_google.setOnClickListener(authentication
				.loginAuthentication(GOOGLE_LOGIN_TAG));

	}

	@Override
	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, responseCode, intent);
		if (facebookCall) {
			
			Session.getActiveSession().onActivityResult(this, requestCode,
					responseCode, intent);
		facebooklogin.callingFBResult(this);
			 
		} else if (Authentication.activityResult == GOOGLE_LOGIN_TAG) {
			authentication.onActivityResult(requestCode, responseCode, intent);
		}
		facebookCall=false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
       if(v.getId()==R.id.btn_fb){
    	   facebookCall=true;
    	   facebooklogin = new FacebookLogin();
			facebooklogin.signInWithFacebook(WelcomeActivity.this);
       }
		
       else if (v.getId() == R.id.btn_signup) {

			String mobile = etxt_mobile_signup.getText().toString().trim();
			if (mobile.length() > 0 && mobile.length() > 9) {

				 Validations.isError(etxt_mobile_signup, false);
				
				Map<String, String> params = new HashMap<String, String>();
				params.put(APIS.MOBILT, mobile);
				authentication.requestSignupAPI(params);
			} else {
				 Validations.isError(etxt_mobile_signup, true);

			}

		} else if (v.getId() == R.id.btn_login) {
			String mobile = etxt_mobile_login.getText().toString().trim();
			String pass = etxt_pass.getText().toString().trim();
			if (mobile.length() > 0 && mobile.length() > 9) {
				 Validations.isError(etxt_mobile_login, false);
				
				if (pass.length() > 0) {
					
					 Validations.isError(etxt_pass, false);
					
					Map<String, String> params = new HashMap<String, String>();
					params.put(APIS.MOBILT, mobile);
					params.put(APIS.PASSWORD, pass);
					authentication.requestLoginAPI(params);
				}else{
					 Validations.isError(etxt_pass, true);
				}

			} else {
				 Validations.isError(etxt_mobile_login, true);
			}
		}
	}
	public void pkghash() {
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					"com.tingler.challenge", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				System.out.println(Base64.encodeToString(md.digest(),
						Base64.NO_WRAP));
			}
		} catch (PackageManager.NameNotFoundException e) {
			Log.d("", e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			Log.d("", e.getMessage(), e);
		}
	}
}
