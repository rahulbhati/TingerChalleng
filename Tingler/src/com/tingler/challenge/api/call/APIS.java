package com.tingler.challenge.api.call;

public class APIS {
	
	public static String HOST="http://rahulparmar.com/ta/api/";
	
	public static String CUSTOM_LOGIN = HOST+"user/login";
	public static String CUSTOM_FORGOTPASSWORD = HOST+"user/forgot_password";
	public static String CUSTOM_SIGNUP = HOST+"user/register_by_mobile";
	public static String CUSTOM_PROFILE = HOST+"user/create_user_profile";
	public static String CUSTOM_Account_Active = HOST+"user/verify_user_account";
	public static String CUSTOM_Social_Media_Login = HOST+"user/login_by_media";
	public static String CUSTOM_CREATE_CHALLENGE = HOST+"challenge/create_challenge";
	public static String CUSTOM_GET_Challenge_Details = HOST+"challenge/get_challenge_details";
	public static String CUSTOM_Get_User_Dashboard = HOST+"user/user_dashboard";
	public static String CUSTOM_Challgenge_Accept = HOST+"challenge/accept_challenge";
	public static String CUSTOM_Challgenge_Reject = HOST+"challenge/reject_challenge";
	public static String CUSTOM_Challgenge_VoteForWitness = HOST+"challenge/vote_for_witness";
	public static String CUSTOM_Challgenge_Remove_User = HOST+"challenge/remove_user_from_challenge";
	
	public static String MOBILT = "mobile";
	public static String Is_verify = "code";
	
	public static String PASSWORD = "password";
	public static String FIRSTNAME = "firstname";
	public static String LASTNAME = "lastname";
	
	public static String STATUS = "status";
	public static String FACEBOOK = "facebook";
	public static String GOOGLE = "google";
	
	public static String Media_type = "media_type";
	public static String Gplus_id = "gplus_id";
	public static String Email = "email";
	
	///        create challenge title/////////
	public static String CC_title = "title";
	public static String CC_description = "description";
	public static String CC_days = "days";
	public static String CC_hrs = "hrs";
	public static String CC_mins = "mins";
	public static String CC_challengee = "challengee";
	public static String CC_witness = "witness";
	public static String CC_coin = "coin";
	public static String CC_user_id = "user_id";
	public static String CC_prize = "custom_prize";
	
	//  Get challenge Details /////////
	public static String Challenge_id="challenge_id";
	public static String Witness_id="witness_id";
	
	
}
