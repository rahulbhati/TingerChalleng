package com.tingler.challenge.api.call;

import java.net.URLEncoder;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tingler.challenge.R;
import com.tingler.challenge.WelcomeActivity;

public class Authentication extends GoogleLogin{
	Context context;
	ProgressDialog progressDialog;
	public static int activityResult = 0;
	public Authentication(Context context) {
		super(context);
		this.context = context;

	}

	public OnClickListener loginAuthentication(final int activityResult) {

		OnClickListener clickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.btn_signup) {
					customSignupAuth();
				}else if (v.getId() == R.id.btn_google) {
					Authentication.activityResult = activityResult;

					googleLogin();
				}else if (v.getId() == R.id.btn_login) {
					customLoginAuth();
				}else if (v.getId() == R.id.btn_account_active) {
					customActiveAccountAuth();
				}
				
			}
		};
		return clickListener;
	}

	public void customActiveAccountAuth() {
		final String mobile = WelcomeActivity.etxt_mobile_login
				.getText().toString().trim();
		final String password = WelcomeActivity.etxt_pass
				.getText().toString().trim();
		progressDialog = ProgressDialog.show(context, "", "loading...");
		String url = null;
		try {
			url = APIS.CUSTOM_LOGIN + "" + APIS.MOBILT + "="
					+ URLEncoder.encode(mobile, "UTF-8")+"&"+APIS.PASSWORD+"="+password;
		} catch (Exception e) {
			System.out.println("Error :" + e);
		}
		System.out.println("URL login :" + url);

		StringRequest loginStringRequest = new StringRequest(url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						System.out.println("Result :" + arg0);

						if (arg0.equalsIgnoreCase("true")) {

							
							
							
						} else {
							Toast.makeText(context,
									"Invalid UserName/Password",
									Toast.LENGTH_LONG).show();
						}
						progressDialog.dismiss();

					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(context,
								"Please check internet connection",
								Toast.LENGTH_LONG).show();
						System.out.println("Volley Error :" + arg0);

						progressDialog.dismiss();
					}
				});

		AppController.getInstance().addToRequestQueue(loginStringRequest);

	}	
	
	
	public void customSignupAuth() {
		final String mobile = WelcomeActivity.etxt_mobile_signup
				.getText().toString().trim();
		progressDialog = ProgressDialog.show(context, "", "loading...");
		String url = null;
		try {
			url = APIS.CUSTOM_SIGNUP + "" + APIS.MOBILT + "="
					+ URLEncoder.encode(mobile, "UTF-8");
		} catch (Exception e) {
			System.out.println("Error :" + e);
		}
		System.out.println("URL login :" + url);

		StringRequest loginStringRequest = new StringRequest(url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						System.out.println("Result :" + arg0);

						if (arg0.equalsIgnoreCase("true")) {

							
							
							
						} else {
							Toast.makeText(context,
									"Invalid UserName/Password",
									Toast.LENGTH_LONG).show();
						}
						progressDialog.dismiss();

					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(context,
								"Please check internet connection",
								Toast.LENGTH_LONG).show();
						System.out.println("Volley Error :" + arg0);

						progressDialog.dismiss();
					}
				});

		AppController.getInstance().addToRequestQueue(loginStringRequest);

	}
	public void customLoginAuth() {
		final String mobile = WelcomeActivity.etxt_mobile_login
				.getText().toString().trim();
		final String password = WelcomeActivity.etxt_pass
				.getText().toString().trim();
		progressDialog = ProgressDialog.show(context, "", "loading...");
		String url = null;
		try {
			url = APIS.CUSTOM_LOGIN + "" + APIS.MOBILT + "="
					+ URLEncoder.encode(mobile, "UTF-8")+"&"+APIS.PASSWORD+"="+password;
		} catch (Exception e) {
			System.out.println("Error :" + e);
		}
		System.out.println("URL login :" + url);

		StringRequest loginStringRequest = new StringRequest(url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						System.out.println("Result :" + arg0);

						if (arg0.equalsIgnoreCase("true")) {

							
							
							
						} else {
							Toast.makeText(context,
									"Invalid UserName/Password",
									Toast.LENGTH_LONG).show();
						}
						progressDialog.dismiss();

					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(context,
								"Please check internet connection",
								Toast.LENGTH_LONG).show();
						System.out.println("Volley Error :" + arg0);

						progressDialog.dismiss();
					}
				});

		AppController.getInstance().addToRequestQueue(loginStringRequest);

	}

}
