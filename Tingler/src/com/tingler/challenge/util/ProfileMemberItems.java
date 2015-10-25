package com.tingler.challenge.util;

public class ProfileMemberItems {
	public String title, profile_img, start_date, end_date, c_time_limit;
	public int progressBarLevel;
	public static String profile_url, default_img_url;

	public int user_type, challenge_id, is_active, c_status, c_progress,is_vote;

	public static String getProfile_url() {
		return profile_url;
	}



	public int getIs_vote() {
		return is_vote;
	}



	public void setIs_vote(int is_vote) {
		this.is_vote = is_vote;
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

	public String getStart_date() {
		return start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public String getC_time_limit() {
		return c_time_limit;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
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

	public int getProgressBarLevel() {
		return progressBarLevel;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public int getUser_type() {
		return user_type;
	}

	public int getChallenge_id() {
		return challenge_id;
	}

	public int getIs_active() {
		return is_active;
	}

	public int getC_status() {
		return c_status;
	}

	public int getC_progress() {
		return c_progress;
	}

	public void setChallenge_id(int challenge_id) {
		this.challenge_id = challenge_id;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	public void setC_status(int c_status) {
		this.c_status = c_status;
	}

	public void setC_progress(int c_progress) {
		this.c_progress = c_progress;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public void setProgressBarLevel(int progressBarLevel) {
		this.progressBarLevel = progressBarLevel;
	}

}
