package com.tingler.challenge.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetChallengeDetailsItems {
public static String c_id,challenger_id,title,description,c_day,c_hrs,c_min,challenge_time,start_date,end_date,is_active,custom_prize,c_coin,winner_id,is_complete,date_added,participants;

public static ArrayList<ParticipantItems> participantItemsArrayList;


public void addGetChallengeDetails(String challengedetails) {
	try {
		JSONObject jsonObject = new JSONObject(challengedetails)
				.getJSONObject("data");
	    setC_id(jsonObject.getString("c_id"));
	    setChallenger_id(jsonObject.getString("challenger_id"));
	    setTitle(jsonObject.getString("title"));
	    setDescription(jsonObject.getString("description"));
	    setC_day(jsonObject.getString("c_day"));
	    setC_hrs(jsonObject.getString("c_hrs"));
	    setC_min(jsonObject.getString("c_min"));
	    setChallenge_time(jsonObject.getString("challenge_time"));
	    setStart_date(jsonObject.getString("start_date"));
	    setEnd_date(jsonObject.getString("end_date"));
	    setIs_active(jsonObject.getString("is_active"));
	    setCustom_prize(jsonObject.getString("custom_prize"));
	    setC_coin(jsonObject.getString("c_coin"));
	    setWinner_id(jsonObject.getString("winner_id"));
	    setIs_complete(jsonObject.getString("is_complete"));
	    setDate_added(jsonObject.getString("date_added"));
	    
	    JSONArray array=new JSONArray(jsonObject.getString("participants"));
	    participantItemsArrayList=new ArrayList<ParticipantItems>();
	    for (int i = 0; i < array.length(); i++) {
			JSONObject object=array.getJSONObject(i);
			ParticipantItems items=new ParticipantItems();
			items.setUser_type(object.getString("user_type"));
			items.setUser_id(object.getString("user_id"));
			items.setContact(object.getString("contact"));
			items.setC_status(object.getString("c_status"));
			items.setName(object.getString("name"));
			items.setProfile_img(object.getString("profile_img"));
			participantItemsArrayList.add(items);
			
		}
	    setParticipantItemsArrayList(participantItemsArrayList);
	    
		
	}catch(Exception e){
		System.out.println(e);
	}
}


public String getC_id() {
	return c_id;
}

public String getChallenger_id() {
	return challenger_id;
}

public String getTitle() {
	return title;
}

public String getDescription() {
	return description;
}

public String getC_day() {
	return c_day;
}

public String getC_hrs() {
	return c_hrs;
}

public String getC_min() {
	return c_min;
}

public String getChallenge_time() {
	return challenge_time;
}

public String getStart_date() {
	return start_date;
}

public String getEnd_date() {
	return end_date;
}

public String getIs_active() {
	return is_active;
}

public String getCustom_prize() {
	return custom_prize;
}

public String getC_coin() {
	return c_coin;
}

public String getWinner_id() {
	return winner_id;
}

public String getIs_complete() {
	return is_complete;
}

public String getDate_added() {
	return date_added;
}

public ArrayList<ParticipantItems> getParticipantItemsArrayList() {
	return participantItemsArrayList;
}

public void setC_id(String c_id) {
	this.c_id = c_id;
}

public void setChallenger_id(String challenger_id) {
	this.challenger_id = challenger_id;
}

public void setTitle(String title) {
	this.title = title;
}

public void setDescription(String description) {
	this.description = description;
}

public void setC_day(String c_day) {
	this.c_day = c_day;
}

public void setC_hrs(String c_hrs) {
	this.c_hrs = c_hrs;
}

public void setC_min(String c_min) {
	this.c_min = c_min;
}

public void setChallenge_time(String challenge_time) {
	this.challenge_time = challenge_time;
}

public void setStart_date(String start_date) {
	this.start_date = start_date;
}

public void setEnd_date(String end_date) {
	this.end_date = end_date;
}

public void setIs_active(String is_active) {
	this.is_active = is_active;
}

public void setCustom_prize(String custom_prize) {
	this.custom_prize = custom_prize;
}

public void setC_coin(String c_coin) {
	this.c_coin = c_coin;
}

public void setWinner_id(String winner_id) {
	this.winner_id = winner_id;
}

public void setIs_complete(String is_complete) {
	this.is_complete = is_complete;
}

public void setDate_added(String date_added) {
	this.date_added = date_added;
}

public void setParticipantItemsArrayList(
		ArrayList<ParticipantItems> participantItemsArrayList) {
	this.participantItemsArrayList = participantItemsArrayList;
}

}
