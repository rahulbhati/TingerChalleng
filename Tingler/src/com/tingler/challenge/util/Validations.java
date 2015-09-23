package com.tingler.challenge.util;



import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;

public class Validations {
	
	
	/**
	 * if status == true means error == red else success
	 * @param view
	 * @param status
	 */
	public static void isError(View view,boolean status){
		if(status){
			GradientDrawable backgroundGradient = (GradientDrawable) view
					.getBackground();
			backgroundGradient.setStroke(1, Color.RED);
		}else{
			GradientDrawable backgroundGradient = (GradientDrawable) view
					.getBackground();
			backgroundGradient.setStroke(1,Color.parseColor("#989898"));
			
		}
	}
	
	
	public static boolean emailValidator(String email) {
		return !TextUtils.isEmpty(email)
				&& android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

	}

	public static boolean phoneValidator(String phone) {
		return !TextUtils.isEmpty(phone)
				&& android.util.Patterns.PHONE.matcher(phone).matches();

	}

	public static String validateUserDetails(String VetxtfName,
			String VetxtlName, String VetxtEmail, String VetxtPhone) {
		String check = null;
		if (!VetxtfName.isEmpty() && !VetxtlName.isEmpty()
				&& !VetxtEmail.isEmpty() && !VetxtPhone.isEmpty()) {
			if (emailValidator(VetxtEmail) && phoneValidator(VetxtPhone)) {

				check = "true";

			} else {
				check = "Please enter valid email or phone";
			}
		} else {
			check = "Please enter information";
		}
		return check;

	}

	public static String validateSignUP(String username, String email,
			String password, String confirm) {
		String check = null;
		if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()
				&& !confirm.isEmpty()) {
			if (emailValidator(email)) {
				if (password.equals(confirm)) {
					check = "true";
				} else {
					check = "Confirm password did not match";
				}
			} else {
				check = "Please enter valid email id";
			}

		} else {
			check = "Please enter information";
		}
		return check;
	}
	public static String validateLogin(String username,String password) {
		String check = null;
		if (!username.isEmpty() && !password.isEmpty()) {
			check="true";
		} else {
			check = "Please enter username/password";
		}
		return check;
	}

}
