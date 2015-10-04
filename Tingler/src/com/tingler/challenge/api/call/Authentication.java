package com.tingler.challenge.api.call;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tingler.challenge.AccountActiveActivity;
import com.tingler.challenge.MainActivity;
import com.tingler.challenge.ProfileActivity;
import com.tingler.challenge.R;
import com.tingler.challenge.WelcomeActivity;
import com.tingler.challenge.fragment.createchallenge.ChallengeCreated;

public class Authentication extends GoogleLogin {
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
				 if (v.getId() == R.id.btn_google) {
					Authentication.activityResult = activityResult;
					googleLogin();
				} else if (v.getId() == R.id.btn_fb) {
					Authentication.activityResult = activityResult;
					
				
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
							System.out.println("JsonObject :" + obj);
							if (obj.getString("data").length() > 0
									&& obj.getString("error").length() == 0) {
								com.tingler.challenge.util.Profile profile = new com.tingler.challenge.util.Profile(
										context);
								profile.addProfileInfo(arg0);
								System.out.println("Id :" + profile.getId());
								Intent intent = new Intent(context,
										AccountActiveActivity.class);
								context.startActivity(intent);
								((Activity)context).startActivity(intent);
							}else{
								Toast.makeText(context, obj.getString("error"), Toast.LENGTH_LONG).show();
							}

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

	public void requestSocialMediaAPI(final Map<String, String> params) {

		progressDialog = ProgressDialog.show(context, "", "loading...");

		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				APIS.CUSTOM_Social_Media_Login, new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						System.out.println("object  :" + arg0);
						try {
							JSONObject obj = new JSONObject(arg0);
							
							if (obj.getString("data").length() > 0
									&& obj.getString("error").length() == 0) {
								com.tingler.challenge.util.Profile profile = new com.tingler.challenge.util.Profile(
										context);
								profile.addProfileInfo(arg0);
								System.out.println("Id :" + profile.getId());
								Intent intent = new Intent(context,
										MainActivity.class);
								context.startActivity(intent);
								((Activity)context).startActivity(intent);
							}else{
								Toast.makeText(context, obj.getString("error"), Toast.LENGTH_LONG).show();
							}

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
						System.out.println("JsonObject :" + arg0);
						try {
							JSONObject obj = new JSONObject(arg0);
							if (obj.getString("data").length() > 0
									&& obj.getString("error").length() == 0) {
								obj.getJSONObject("data")
										.remove(com.tingler.challenge.util.Profile.PASSWORD);
								obj.getJSONObject("data")
										.put(com.tingler.challenge.util.Profile.PASSWORD,
												params.get(params.get( com.tingler.challenge.util.Profile.PASSWORD)));

								com.tingler.challenge.util.Profile profile = new com.tingler.challenge.util.Profile(
										context);
								profile.addProfileInfo(arg0);
								
								if(com.tingler.challenge.util.Profile.getStatus().equalsIgnoreCase("2")){
								
								Intent intent = new Intent(context,
										ProfileActivity.class);
								context.startActivity(intent);
								((Activity)context).finish();
								}else{
									Intent intent = new Intent(context,
											MainActivity.class);
									context.startActivity(intent);
									((Activity)context).finish();	
								}

							}else{
								Toast.makeText(context, obj.getString("error"), Toast.LENGTH_LONG).show();
							}

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
						System.out.println("data :" + arg0);
						try {

							JSONObject obj = new JSONObject(arg0);
							
							if (obj.getString("data").length() > 0
									&& obj.getString("error").length() == 0) {
								obj.getJSONObject("data")
										.remove(com.tingler.challenge.util.Profile.PASSWORD);
								obj.getJSONObject("data")
										.put(com.tingler.challenge.util.Profile.PASSWORD,
												params.get(com.tingler.challenge.util.Profile.PASSWORD));

								com.tingler.challenge.util.Profile profile = new com.tingler.challenge.util.Profile(
										context);
								profile.addProfileInfo(obj.toString());

								System.out.println("Id :" + profile.getId());
								Intent intent = new Intent(context,
										MainActivity.class);
								context.startActivity(intent);
								((Activity) context).finish();
							}
							else{
								Toast.makeText(context, obj.getString("error"), Toast.LENGTH_LONG).show();
							}
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

	public void requestActiveAccountAPI(final Map<String, String> params) {

		progressDialog = ProgressDialog.show(context, "", "loading...");

		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				APIS.CUSTOM_Account_Active, new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub

						try {
							JSONObject obj = new JSONObject(arg0);
							System.out.println("Obj :" + obj);

							if (obj.getString("data").length() > 0
									&& obj.getString("error").length() == 0) {

								com.tingler.challenge.util.Profile profile = new com.tingler.challenge.util.Profile(
										context);
								profile.addProfileInfo(arg0);
								Intent intent = new Intent(context,
										ProfileActivity.class);
								context.startActivity(intent);
								((Activity)context).startActivity(intent);
							}else{
								Toast.makeText(context, obj.getString("error"), Toast.LENGTH_LONG).show();
							}

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

	public void requestCreateChallengeAPI(final Map<String, String> params) {

		progressDialog = ProgressDialog.show(context, "", "loading...");

		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				APIS.CUSTOM_CREATE_CHALLENGE, new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
                           System.out.println("response :"+arg0);
						/*try {
							JSONObject obj = new JSONObject(arg0);
							System.out.println("Obj :" + obj);

							if (obj.getString("data").length() > 0
									&& obj.getString("error").length() == 0) {

							
							}else{
								Toast.makeText(context, obj.getString("error"), Toast.LENGTH_LONG).show();
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}*/
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
	
	public void requestForgotAPI(final Map<String, String> params) {

		progressDialog = ProgressDialog.show(context, "", "loading...");

		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				APIS.CUSTOM_FORGOTPASSWORD, new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						System.out.println("JsonObject :" + arg0);
						try {
							JSONObject obj = new JSONObject(arg0);
							if (obj.getString("error").length() == 0) {
								com.tingler.challenge.util.Profile profile = new com.tingler.challenge.util.Profile(
										context);
								
							
								
								profile.setMobile(params.get(APIS.MOBILT));
								
								Intent intent = new Intent(context,
										AccountActiveActivity.class);
								context.startActivity(intent);
								((Activity)context).finish();
								
							}else{
								Toast.makeText(context, obj.getString("error"), Toast.LENGTH_LONG).show();
							}

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

	public void requestEditProfileAPI(final Map<String, String> params) {

		progressDialog = ProgressDialog.show(context, "", "loading...");

		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				APIS.CUSTOM_PROFILE, new Response.Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						System.out.println("data :" + arg0);
						try {

							JSONObject obj = new JSONObject(arg0);
							
							if (obj.getString("data").length() > 0
									&& obj.getString("error").length() == 0) {
								obj.getJSONObject("data")
										.remove(com.tingler.challenge.util.Profile.PASSWORD);
								obj.getJSONObject("data")
										.put(com.tingler.challenge.util.Profile.PASSWORD,
												params.get(com.tingler.challenge.util.Profile.PASSWORD));

								com.tingler.challenge.util.Profile profile = new com.tingler.challenge.util.Profile(
										context);
								profile.addProfileInfo(obj.toString());

								System.out.println("Id :" + profile.getId());
								Intent intent = new Intent(context,
										MainActivity.class);
								context.startActivity(intent);
								((Activity) context).finish();
							}
							else{
								Toast.makeText(context, obj.getString("error"), Toast.LENGTH_LONG).show();
							}
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
	
}
