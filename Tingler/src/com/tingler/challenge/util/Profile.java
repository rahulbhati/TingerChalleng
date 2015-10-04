package com.tingler.challenge.util;

import java.io.ByteArrayOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

public class Profile {
	public static final String MYProfile = "MyProfilePre";
	public static SharedPreferences sharedpreferences;
	public static final String ID = "id";

	public static final String MOBILE = "mobile";
	public static final String PASSWORD = "password";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String EMAIL = "email";

	public static final String STATUS_MSG = "status_msg";
	public static final String STATUS = "status";

	public static final String LEVEL = "user_level";
	public static final String POINTS = "user_points";
	public static final String FACEBOOK_ID = "fb_id";
	public static final String GOOGLE_ID = "gp_id";
	public static final String COINS = "coins";
	public static final String IS_VARIFY = "is_verify";
	public static final String DATE_ADDED = "date_added";
	public static final String Verification_Code = "verification_code";
	public static final String Change_Password_Code = "change_pwd_code";
	public static final String Profile_Img = "profile_img";
	public static final String Media_Type = "media_type";

	public static String getMediaType() {
		return sharedpreferences.getString(Media_Type, "");
	}

	public static String getChangePasswordCode() {
		return Change_Password_Code;
	}

	public static String getProfileImg() {
		return sharedpreferences.getString(Profile_Img, "");
	}

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
			editor.putString(STATUS, jsonObject.getString(STATUS));
			editor.putString(DATE_ADDED, jsonObject.getString(DATE_ADDED));
			editor.putString(FACEBOOK_ID, jsonObject.getString(FACEBOOK_ID));
			editor.putString(GOOGLE_ID, jsonObject.getString(GOOGLE_ID));
			editor.putString(IS_VARIFY, jsonObject.getString(IS_VARIFY));

			editor.putString(Verification_Code,
					jsonObject.getString(Verification_Code));

			editor.putString(COINS, jsonObject.getString(COINS));
			editor.putString(POINTS, jsonObject.getString(POINTS));
			editor.putString(LEVEL, jsonObject.getString(LEVEL));
			editor.putString(Change_Password_Code,
					jsonObject.getString(Change_Password_Code));
            
			editor.commit();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getId() {
		return sharedpreferences.getString(ID, "");
	}

	public void setMobile(String mobile){
		editor.putString(MOBILE,mobile);
		editor.commit();
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
	
	public void setFirstName(String first_Name){
		editor.putString(FIRST_NAME, first_Name);
		editor.commit();
	}

	public static String getLastName() {

		return sharedpreferences.getString(LAST_NAME, "");
	}
	public void setLastName(String LastName){
		editor.putString(LAST_NAME, LastName);
		editor.commit();
	}
	public void setProfileBase64(String profileBit64){
		editor.putString(Profile_Img, profileBit64);
		editor.commit();
	}
	
	public String getProfileBase64(){
		return sharedpreferences.getString(Profile_Img, "");
	}
	
	public static String getFullName(){
		return getFirstName()+" "+getLastName();
	}
	public static String getEmail() {

		return sharedpreferences.getString(EMAIL, "");
	}
	public void setEmail(String Email){
		editor.putString(EMAIL, Email);
		editor.commit();
	}
	public void setMediaType(String MediaType){
		editor.putString(Media_Type, MediaType);
		editor.commit();
	}
	public static String getStatusMsg() {

		return sharedpreferences.getString(STATUS_MSG, "");
	}

	public static String getStatus() {

		return sharedpreferences.getString(STATUS, "");
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
	public void setFacebookId(String FacebookId){
		editor.putString(FACEBOOK_ID, FacebookId);
		editor.commit();
	}
	public static String getGoogleId() {

		return sharedpreferences.getString(GOOGLE_ID, "");
	}
	public void setGoogleId(String GoogleId){
		editor.putString(GOOGLE_ID, GoogleId);
		editor.commit();
	}
	public void setProfileImage(String ProfileImage){
		editor.putString(Profile_Img, ProfileImage);
		editor.commit();
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
	public static String encodeTobase64(Bitmap image) {
		Bitmap immagex = image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

		Log.e("LOOK", imageEncoded);
		return imageEncoded;
	}

	public static Bitmap decodeBase64(String input) {
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}
	public Bitmap getCroppedBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);

		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, 100,
				paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public Bitmap getCroppedBitmapCamera(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);

		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, 70,
				paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

}
