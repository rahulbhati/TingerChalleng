package com.tingler.challenge.fragment;

import java.util.HashMap;
import java.util.Map;

import com.tingler.challenge.R;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.Profile;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProfile extends Fragment implements OnClickListener{
	Button btn_clear, btn_save;
	ImageView imageview_profile;
	public static EditText etxt_fname, etxt_lname, etxt_email, etxt_contact,
			etxt_password, etxt_cpassword;
	Profile profile;
	Authentication authentication;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View editView = inflater.inflate(R.layout.fragment_edit, container,
				false);
		init(editView);
		return editView;
	}

	public void init(View editView) {
		profile = new Profile(getActivity());
		authentication = new Authentication(getActivity());
		btn_clear = (Button)editView. findViewById(R.id.btn_clear);
		btn_save = (Button)editView. findViewById(R.id.btn_save);
		btn_save.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		imageview_profile = (ImageView)editView. findViewById(R.id.imageview_profile);

		etxt_fname = (EditText)editView. findViewById(R.id.etxt_fname);
		etxt_lname = (EditText)editView. findViewById(R.id.etxt_lname);
		etxt_email = (EditText)editView. findViewById(R.id.etxt_email);

		etxt_contact = (EditText) editView.findViewById(R.id.etxt_contact);
		etxt_password = (EditText)editView. findViewById(R.id.etxt_password);
		etxt_cpassword = (EditText)editView. findViewById(R.id.etxt_cpassword);
		setValues();
	}

	public void setValues() {

		etxt_fname.setText(profile.getFirstName());
		etxt_lname.setText(profile.getLastName());
		etxt_email.setText(profile.getEmail());
	
		etxt_contact.setText(profile.getMobile());
		etxt_password.setText(profile.getPassword());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_clear) {
			etxt_fname.getText().clear();
			
			etxt_lname.getText().clear();
			etxt_email.getText().clear();
			etxt_contact.getText().clear();
			etxt_password.getText().clear();
			etxt_cpassword.getText().clear();
		}else if (v.getId() == R.id.btn_save) {
			Profile profile = new Profile(getActivity());
			Bitmap bitmap = ((BitmapDrawable) imageview_profile.getDrawable())
					.getBitmap();

			// String picbase64=encodeTobase64(bitmap);

			Map<String, String> params = new HashMap<String, String>();

			params.put(Profile.FIRST_NAME, etxt_fname.getText().toString()
					.trim());
			params.put(Profile.LAST_NAME, etxt_lname.getText().toString()
					.trim());
			params.put(Profile.EMAIL, etxt_email.getText().toString().trim());
			params.put(Profile.MOBILE, etxt_contact.getText().toString().trim());
			params.put(Profile.PASSWORD, etxt_password.getText().toString()
					.trim());
			// params.put(Profile.Profile_Img,"profile image");
			params.put(Profile.ID, profile.getId());
			params.put(Profile.STATUS_MSG, "2");
			if (profile.getGoogleId().length() > 0
					|| profile.getFacebookId().length() > 0) {

				params.put(Profile.Media_Type, profile.getMediaType()
						.toString().trim());
				if (profile.getMediaType().equalsIgnoreCase("facebook")) {
					params.put(Profile.FACEBOOK_ID, profile.getFacebookId()
							.toString().trim());
				} else {
					params.put(Profile.GOOGLE_ID, profile.getGoogleId()
							.toString().trim());

				}
				authentication.requestSocialMediaAPI(params);
			} else {
				authentication.requestProfileAPI(params);
			}

		} 
	}

}
