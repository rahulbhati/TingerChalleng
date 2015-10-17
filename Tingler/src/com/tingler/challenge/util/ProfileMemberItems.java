package com.tingler.challenge.util;

public class ProfileMemberItems {
public String challenge_id, title,profile_img,user_type,c_status,is_active,start_date,end_date,c_progress,c_time_limit;
public int progressBarLevel;
public static String profile_url,default_img_url;
public String getChallenge_id() {
	return challenge_id;
}


public static String getProfile_url() {
	return profile_url;
}


public static String getDefault_img_url() {
	return default_img_url;
}


public static void setProfile_url(String profile_url) {
	ProfileMemberItems.profile_url = profile_url;
}


public static void setDefault_img_url(String default_img_url) {
	ProfileMemberItems.default_img_url = default_img_url;
}


public String getIs_active() {
	return is_active;
}

public String getStart_date() {
	return start_date;
}

public String getEnd_date() {
	return end_date;
}

public String getC_progress() {
	return c_progress;
}

public String getC_time_limit() {
	return c_time_limit;
}

public void setIs_active(String is_active) {
	this.is_active = is_active;
}

public void setStart_date(String start_date) {
	this.start_date = start_date;
}

public void setEnd_date(String end_date) {
	this.end_date = end_date;
}

public void setC_progress(String c_progress) {
	this.c_progress = c_progress;
}

public void setC_time_limit(String c_time_limit) {
	this.c_time_limit = c_time_limit;
}

public String getTitle() {
	return title;
}
public String getProfile_img() {
	return profile_img;
}
public String getUser_type() {
	return user_type;
}
public String getC_status() {
	return c_status;
}
public int getProgressBarLevel() {
	return progressBarLevel;
}
public void setChallenge_id(String challenge_id) {
	this.challenge_id = challenge_id;
}
public void setTitle(String title) {
	this.title = title;
}
public void setProfile_img(String profile_img) {
	this.profile_img = profile_img;
}
public void setUser_type(String user_type) {
	this.user_type = user_type;
}
public void setC_status(String c_status) {
	this.c_status = c_status;
}
public void setProgressBarLevel(int progressBarLevel) {
	this.progressBarLevel = progressBarLevel;
}

}
