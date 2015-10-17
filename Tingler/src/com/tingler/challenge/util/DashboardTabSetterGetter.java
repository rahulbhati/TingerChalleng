package com.tingler.challenge.util;

import java.util.ArrayList;

public class DashboardTabSetterGetter {
	public static ArrayList<ProfileMemberItems> challengeArrayList, witnessArrayList,
	watcherArrayList;

	public static ArrayList<ProfileMemberItems> getChallengeArrayList() {
		return challengeArrayList;
	}

	public static ArrayList<ProfileMemberItems> getWitnessArrayList() {
		return witnessArrayList;
	}

	public static ArrayList<ProfileMemberItems> getWatcherArrayList() {
		return watcherArrayList;
	}

	public static void setChallengeArrayList(
			ArrayList<ProfileMemberItems> challengeArrayList) {
		DashboardTabSetterGetter.challengeArrayList = challengeArrayList;
	}

	public static void setWitnessArrayList(
			ArrayList<ProfileMemberItems> witnessArrayList) {
		DashboardTabSetterGetter.witnessArrayList = witnessArrayList;
	}

	public static void setWatcherArrayList(
			ArrayList<ProfileMemberItems> watcherArrayList) {
		DashboardTabSetterGetter.watcherArrayList = watcherArrayList;
	}
	
}
