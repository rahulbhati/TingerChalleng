package com.tingler.challenge.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;

public class Profile {
	public static final String MYProfile = "MyProfilePre";
	public static SharedPreferences sharedpreferences;
	private static final String ID = "id";
	private static final String SOCIAL = "social";
	private static final String MOBILE = "mobile";
	private static final String PASSWORD = "password";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String EMAIL = "email";

	private static final String STATUS_MSG = "status_msg";
	private static final String STATUS = "status";
	private static final String PICTURE = "picture";
	private static final String LEVEL = "level";
	private static final String POINTS = "points";
	private static final String FACEBOOK_ID = "fb_id";
	private static final String GOOGLE_ID = "gp_id";
	private static final String COINS = "coins";
	private static final String IS_VARIFY = "is_verify";
	private static final String DATE_ADDED = "date_added";
	private static final String Verification_Code = "verification_code";
	public Context context;
	SharedPreferences.Editor editor;

	public Profile(Context context) {
		this.context = context;
		sharedpreferences = this.context.getSharedPreferences(MYProfile,
				Context.MODE_PRIVATE);

		editor = sharedpreferences.edit();
	}

	public void addProfileInfo(String profileInfo) {
		try {
			JSONObject jsonObject = new JSONObject(profileInfo)
					.getJSONObject("data");
			editor.putString(ID, jsonObject.getString(ID));
			editor.putString(EMAIL, jsonObject.getString(EMAIL));
			editor.putString(PASSWORD, jsonObject.getString(PASSWORD));
			editor.putString(FIRST_NAME, jsonObject.getString(FIRST_NAME));
			editor.putString(LAST_NAME, jsonObject.getString(LAST_NAME));
			editor.putString(MOBILE, jsonObject.getString(MOBILE));
			editor.putString(STATUS_MSG, jsonObject.getString(STATUS_MSG));
			editor.putString(DATE_ADDED, jsonObject.getString(DATE_ADDED));
			editor.putString(FACEBOOK_ID, jsonObject.getString(FACEBOOK_ID));
			editor.putString(GOOGLE_ID, jsonObject.getString(GOOGLE_ID));
			editor.putString(IS_VARIFY, jsonObject.getString(IS_VARIFY));
			editor.putString(STATUS, jsonObject.getString(STATUS));
			editor.putString(Verification_Code,
					jsonObject.getString(Verification_Code));
			editor.putString(COINS, jsonObject.getString(COINS));
			editor.commit();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getId() {
		return sharedpreferences.getString(ID, "");
	}

	public static String getSocial() {
		return SOCIAL;
	}

	public static String getMobile() {

		return sharedpreferences.getString(MOBILE, "");
	}

	public static String getPassword() {

		return sharedpreferences.getString(PASSWORD, "");
	}

	public static String getFirstName() {

		return sharedpreferences.getString(FIRST_NAME, "");
	}

	public static String getLastName() {

		return sharedpreferences.getString(LAST_NAME, "");
	}

	public static String getEmail() {

		return sharedpreferences.getString(EMAIL, "");
	}

	public static String getStatusMsg() {

		return sharedpreferences.getString(STATUS_MSG, "");
	}

	public static String getStatus() {

		return sharedpreferences.getString(STATUS, "");
	}

	public static String getPicture() {

		return sharedpreferences.getString(PICTURE, "");
	}

	public static String getLevel() {

		return sharedpreferences.getString(LEVEL, "");
	}

	public static String getPoints() {
		return sharedpreferences.getString(POINTS, "");

	}

	public static String getFacebookId() {
		return sharedpreferences.getString(FACEBOOK_ID, "");

	}

	public static String getGoogleId() {

		return sharedpreferences.getString(GOOGLE_ID, "");
	}

	public static String getCoins() {
		return sharedpreferences.getString(COINS, "");
	}

	public static String getIsVarify() {

		return sharedpreferences.getString(IS_VARIFY, "");
	}

	public static String getDateAdded() {

		return sharedpreferences.getString(DATE_ADDED, "");
	}

	public static String getVerificationCode() {

		return sharedpreferences.getString(Verification_Code, "");
	}

}
