package com.tingler.challenge.util;

import java.util.ArrayList;

public class NotificationSetterGetter {
public static ArrayList<NotificationItems> arrayList;

public static ArrayList<NotificationItems> getArrayList() {
	return arrayList;
}

public static void setArrayList(ArrayList<NotificationItems> arrayList) {
	NotificationSetterGetter.arrayList = arrayList;
}

}
