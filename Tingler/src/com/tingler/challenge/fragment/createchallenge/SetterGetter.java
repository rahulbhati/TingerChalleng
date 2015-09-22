package com.tingler.challenge.fragment.createchallenge;

import java.util.ArrayList;

import com.tingler.challenge.util.ContactItem;

public class SetterGetter {
public static ArrayList<ContactItem> challengeeTempArrayList=new ArrayList<ContactItem>();
public static ArrayList<ContactItem> witnessTempArrayList=new ArrayList<ContactItem>();
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
};


}
