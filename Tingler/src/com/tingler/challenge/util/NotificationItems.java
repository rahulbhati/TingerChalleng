package com.tingler.challenge.util;

public class NotificationItems {
public  String message,sub_message,send_date;
public  int n_id,notification_type,user_id,challenge_id,status;
public String getMessage() {
	return message;
}
public String getSub_message() {
	return sub_message;
}
public String getSend_date() {
	return send_date;
}
public int getN_id() {
	return n_id;
}
public int getNotification_type() {
	return notification_type;
}
public int getUser_id() {
	return user_id;
}
public int getChallenge_id() {
	return challenge_id;
}
public int getStatus() {
	return status;
}
public void setMessage(String message) {
	this.message = message;
}
public void setSub_message(String sub_message) {
	this.sub_message = sub_message;
}
public void setSend_date(String send_date) {
	this.send_date = send_date;
}
public void setN_id(int n_id) {
	this.n_id = n_id;
}
public void setNotification_type(int notification_type) {
	this.notification_type = notification_type;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public void setChallenge_id(int challenge_id) {
	this.challenge_id = challenge_id;
}
public void setStatus(int status) {
	this.status = status;
}

}
