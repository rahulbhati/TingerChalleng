package com.tingler.challenge.util;

public class SetterGetter {
public static int userType;

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
