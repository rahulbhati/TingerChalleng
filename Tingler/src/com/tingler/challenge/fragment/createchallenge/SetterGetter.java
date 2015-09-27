package com.tingler.challenge.fragment.createchallenge;

import java.util.ArrayList;

import com.tingler.challenge.util.ContactItem;

public class SetterGetter {
public static ArrayList<ContactItem> challengeeTempArrayList=new ArrayList<ContactItem>();
public static ArrayList<ContactItem> witnessTempArrayList=new ArrayList<ContactItem>();
public static String title,description,days,hours,minutes;

public static String challengeTitle=null;
public static String challengeDescription=null;
public static String challengeDays=null,challengeHours=null,challengeMinutes=null;

public static String getChallengeTitle() {
	return challengeTitle;
}

public static String getChallengeDescription() {
	return challengeDescription;
}

public static String getChallengeDays() {
	return challengeDays;
}

public static String getChallengeHours() {
	return challengeHours;
}

public static String getChallengeMinutes() {
	return challengeMinutes;
}

public static void setChallengeTitle(String challengeTitle) {
	SetterGetter.challengeTitle = challengeTitle;
}

public static void setChallengeDescription(String challengeDescription) {
	SetterGetter.challengeDescription = challengeDescription;
}

public static void setChallengeDays(String challengeDays) {
	SetterGetter.challengeDays = challengeDays;
}

public static void setChallengeHours(String challengeHours) {
	SetterGetter.challengeHours = challengeHours;
}

public static void setChallengeMinutes(String challengeMinutes) {
	SetterGetter.challengeMinutes = challengeMinutes;
}

public static ArrayList<ContactItem> getChallengeeTempArrayList() {
	return challengeeTempArrayList;
}

public static void setChallengeeTempArrayList(
		ArrayList<ContactItem> challengeeTempArrayList) {
	SetterGetter.challengeeTempArrayList = challengeeTempArrayList;
}

public static ArrayList<ContactItem> getWitnessTempArrayList() {
	return witnessTempArrayList;
}

public static void setWitnessTempArrayList(
		ArrayList<ContactItem> witnessTempArrayList) {
	SetterGetter.witnessTempArrayList = witnessTempArrayList;
}

public static String getTitle() {
	return title;
}

public static String getDescription() {
	return description;
}

public static String getDays() {
	return days;
}

public static String getHours() {
	return hours;
}

public static String getMinutes() {
	return minutes;
}

public static void setTitle(String title) {
	SetterGetter.title = title;
}

public static void setDescription(String description) {
	SetterGetter.description = description;
}

public static void setDays(String days) {
	SetterGetter.days = days;
}

public static void setHours(String hours) {
	SetterGetter.hours = hours;
}

public static void setMinutes(String minutes) {
	SetterGetter.minutes = minutes;
};


}
