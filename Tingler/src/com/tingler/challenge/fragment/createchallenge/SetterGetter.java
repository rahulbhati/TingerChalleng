package com.tingler.challenge.fragment.createchallenge;

import java.util.ArrayList;

import com.tingler.challenge.util.ContactItem;

public class SetterGetter {
public static ArrayList<ContactItem> tempArrayList=new ArrayList<ContactItem>();;

public static ArrayList<ContactItem> getTempArrayList() {
	return tempArrayList;
}

public static void setTempArrayList(ArrayList<ContactItem> tempArrayList) {
	SetterGetter.tempArrayList = tempArrayList;
}

}
