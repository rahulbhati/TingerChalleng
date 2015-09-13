package com.tingler.challenge.util;

import android.content.SharedPreferences;

public class Profile {
	 public static final String MYProfile = "MyProfilePre" ;
	 public static SharedPreferences sharedpreferences;
	 private static final String SOCIAL="social";
	 private static final String MOBILE="mobile";
	 private static final String PASSWORD="password";
	 private static final String FIRST_NAME="firstname";
	 private static final String LAST_NAME="lastname";
	 private static final String EMAIL="email";
	 private static final String COUNTRY="country";
	 private static final String CITY="city";
	 private static final String GENDER="gender";
	 private static final String STATUS="status";
	 private static final String PICTURE="picture";
	 private static final String LEVEL="level";
	 private static final String POINTS="points";
	 private static final String COINS="coins";
	
	 
	 
	 
	public static String getMyprofile() {
		return MYProfile;
	}
	public static String getSocial() {
		return SOCIAL;
	}
	public static String getMobile() {
		return MOBILE;
	}
	public static String getPassword() {
		return PASSWORD;
	}
	public static String getFirstName() {
		return FIRST_NAME;
	}
	public static String getLastName() {
		return LAST_NAME;
	}
	public static String getEmail() {
		return EMAIL;
	}
	public static String getCountry() {
		return COUNTRY;
	}
	public static String getCity() {
		return CITY;
	}
	public static String getGender() {
		return GENDER;
	}
	public static String getStatus() {
		return STATUS;
	}
	public static String getPicture() {
		return PICTURE;
	}
	public static String getLevel() {
		return LEVEL;
	}
	public static String getPoints() {
		return POINTS;
	}
	public static String getCoins() {
		return COINS;
	}
	 
	 
	 
	 
}
