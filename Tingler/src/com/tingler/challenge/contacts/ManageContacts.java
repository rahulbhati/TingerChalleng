package com.tingler.challenge.contacts;

import java.util.ArrayList;

import com.tingler.challenge.util.ContactItem;

public class ManageContacts {
public static ArrayList<ContactItem> primaryContactArrayList,challengeeContactArrayList,witnessContactArrayList;


public static ArrayList<ContactItem> getWitnessContactArrayList() {
	return witnessContactArrayList;
}

public static void setWitnessContactArrayList(
		ArrayList<ContactItem> witnessContactArrayList) {
	ManageContacts.witnessContactArrayList = witnessContactArrayList;
}

public static ArrayList<ContactItem> getChallengeeContactArrayList() {
	return challengeeContactArrayList;
}

public static void setChallengeeContactArrayList(
		ArrayList<ContactItem> challengeeContactArrayList) {
	ManageContacts.challengeeContactArrayList = challengeeContactArrayList;
}

public static ArrayList<ContactItem> getPrimaryContactArrayList() {
	return primaryContactArrayList;
}

public static void setPrimaryContactArrayList(
		ArrayList<ContactItem> primaryContactArrayList) {
	ManageContacts.primaryContactArrayList = primaryContactArrayList;
}

}
