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

public static ArrayList<ContactItem> getCheckedChallengee(){
	ArrayList<ContactItem> arrayList=new ArrayList<ContactItem>();
	
	for(int i=0;i<getChallengeeContactArrayList().size();i++){
		   if(getChallengeeContactArrayList().get(i).isCheckboxstatus()){
			   arrayList.add(getChallengeeContactArrayList().get(i));
		   }
	}
	return arrayList;
}
public static ArrayList<ContactItem> getCheckedWitness(){
	ArrayList<ContactItem> arrayList=new ArrayList<ContactItem>();
	
	for(int i=0;i<getWitnessContactArrayList().size();i++){
		   if(getWitnessContactArrayList().get(i).isCheckboxstatus()){
			   arrayList.add(getWitnessContactArrayList().get(i));
		   }
	}
	return arrayList;
}

}
