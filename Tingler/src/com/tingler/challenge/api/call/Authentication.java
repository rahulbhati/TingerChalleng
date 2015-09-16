package com.tingler.challenge.api.call;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.provider.ContactsContract.Profile;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.tingler.challenge.R;
import com.tingler.challenge.WelcomeActivity;

public class Authentication extends GoogleLogin {
	Context context;
	ProgressDialog progressDialog;
	public static int activityResult = 0;
	JSONObject responseAPI = null;

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
					// customSignupAuth();
					
				} else if (v.getId() == R.id.btn_google) {
					Authentication.activityResult = activityResult;

					googleLogin();
				} else if (v.getId() == R.id.btn_login) {
					

				} else if (v.getId() == R.id.btn_account_active) {
					customActiveAccountAuth();
				}

			}
		};
		return clickListener;
	}

	
	
	public void requestSignupAPI(final Map<String, String> params) {

		progressDialog = ProgressDialog.show(context, "", "loading...");

		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				APIS.CUSTOM_SIGNUP, new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub

						try {
							JSONObject obj = new JSONObject(arg0);
							responseAPI = obj;
							System.out.println("JsonObject :" + responseAPI);
                            com.tingler.challenge.util.Profile profile=new com.tingler.challenge.util.Profile(context);
                            profile.addProfileInfo(arg0);
                            System.out.println("Id :"+profile.getId());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> parameters = new HashMap<String, String>();
				parameters = params;
				return parameters;
			}
		};

		AppController.getInstance().addToRequestQueue(stringRequest);

		
	}

	public void requestLoginAPI(final Map<String, String> params) {

		progressDialog = ProgressDialog.show(context, "", "loading...");

		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				APIS.CUSTOM_LOGIN, new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub

						try {
							JSONObject obj = new JSONObject(arg0);
							responseAPI = obj;
							System.out.println("JsonObject :" + responseAPI);

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> parameters = new HashMap<String, String>();
				parameters = params;
				return parameters;
			}
		};

		AppController.getInstance().addToRequestQueue(stringRequest);

		
	}

	public void requestProfileAPI(final Map<String, String> params) {

		progressDialog = ProgressDialog.show(context, "", "loading...");

		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				APIS.CUSTOM_PROFILE, new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						
						try {
							JSONObject obj = new JSONObject(arg0);
							responseAPI = obj;
							System.out.println("JsonObject :" + responseAPI);
                            com.tingler.challenge.util.Profile profile=new com.tingler.challenge.util.Profile(context);
                            profile.addProfileInfo(arg0);
                            System.out.println("Id :"+profile.getId());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> parameters = new HashMap<String, String>();
				parameters = params;
				return parameters;
			}
		};

		AppController.getInstance().addToRequestQueue(stringRequest);

		
	}

	
	public void customActiveAccountAuth() {
		final String mobile = WelcomeActivity.etxt_mobile_login.getText()
				.toString().trim();
		final String password = WelcomeActivity.etxt_pass.getText().toString()
				.trim();
		progressDialog = ProgressDialog.show(context, "", "loading...");
		String url = null;
		try {
			url = APIS.CUSTOM_LOGIN + "" + APIS.MOBILT + "="
					+ URLEncoder.encode(mobile, "UTF-8") + "&" + APIS.PASSWORD
					+ "=" + password;
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
		final String mobile = WelcomeActivity.etxt_mobile_signup.getText()
				.toString().trim();
		progressDialog = ProgressDialog.show(context, "", "loading...");

		StringRequest signupStringRequest = new StringRequest(
				Request.Method.POST, APIS.CUSTOM_SIGNUP,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						System.out.println("Result :" + arg0);

						try {
							JSONObject obj = new JSONObject(arg0);
							System.out.println("JsonObject :" + obj);

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put(APIS.MOBILT, mobile);
				return params;
			}
		};

		AppController.getInstance().addToRequestQueue(signupStringRequest);

		// AppController.getInstance().addToRequestQueue(singupArrayRequest);

	}

	public void customLoginAuth() {
		final String mobile = WelcomeActivity.etxt_mobile_login.getText()
				.toString().trim();
		final String password = WelcomeActivity.etxt_pass.getText().toString()
				.trim();
		progressDialog = ProgressDialog.show(context, "", "loading...");
		String url = null;
		try {
			url = APIS.CUSTOM_LOGIN + "" + APIS.MOBILT + "="
					+ URLEncoder.encode(mobile, "UTF-8") + "&" + APIS.PASSWORD
					+ "=" + password;
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
