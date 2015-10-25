package com.tingler.challenge.util;

public class SetterGetter {
public static int userType,currentChallenge_id;

public static int getCurrentChallenge_id() {
	return currentChallenge_id;
}

public static void setCurrentChallenge_id(int currentChallenge_id) {
	SetterGetter.currentChallenge_id = currentChallenge_id;
}

/**
 * Selected challenge current user type
 * @return
 */
public static int getUserType() {
	return userType;
}

/**
 * Selected challenge current user type
 * @param userType
 */
public static void setUserType(int userType) {
	SetterGetter.userType = userType;
}

}
