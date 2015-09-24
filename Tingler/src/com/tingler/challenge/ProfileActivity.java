package com.tingler.challenge;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.Profile;
import com.tingler.challenge.util.Validations;

public class ProfileActivity extends Activity implements OnClickListener {

	Authentication authentication;
	Button btn_account_active;
	private static int ACTIVE_ACCOUNT = 6;
	TextView toolbar_title;
	Button btn_clear, btn_save;
	ImageView imageview_profile;
	public static EditText etxt_fname, etxt_lname, etxt_email, etxt_contact,
			etxt_password, etxt_cpassword;
	Profile profile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		init();
	}

	public void init() {
		profile = new Profile(this);
		authentication = new Authentication(this);
		toolbar_title = (TextView) findViewById(R.id.toolbar_title);
		toolbar_title.setText("Active Account");
		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_save.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		imageview_profile = (ImageView) findViewById(R.id.imageview_profile);

		etxt_fname = (EditText) findViewById(R.id.etxt_fname);
		etxt_lname = (EditText) findViewById(R.id.etxt_lname);
		etxt_email = (EditText) findViewById(R.id.etxt_email);

		etxt_contact = (EditText) findViewById(R.id.etxt_contact);
		etxt_password = (EditText) findViewById(R.id.etxt_password);
		etxt_cpassword = (EditText) findViewById(R.id.etxt_cpassword);
		setValues();
	}

	public void setValues() {

		etxt_fname.setText(profile.getFirstName());
		etxt_lname.setText(profile.getLastName());
		etxt_email.setText(profile.getEmail());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.btn_save) {
			String fname=etxt_fname.getText().toString()
					.trim();
			String lname=etxt_lname.getText().toString()
					.trim();
			String email=etxt_email.getText().toString()
					.trim();
			String mobile=etxt_contact.getText().toString()
					.trim();
			String pass=etxt_password.getText().toString()
					.trim();
			String cpass=etxt_cpassword.getText().toString()
					.trim();
			Validations.isError(etxt_fname, false);
			Validations.isError(etxt_lname, false);
			Validations.isError(etxt_email, false);
			Validations.isError(etxt_contact, false);
			Validations.isError(etxt_password, false);
			Validations.isError(etxt_cpassword, false);
			if(fname.length()>0  ){
				Validations.isError(etxt_fname, false);
				if(lname.length()>0  ){
					Validations.isError(etxt_lname, false);
					if(email.length()>0 && Validations.emailValidator(email) ){
						Validations.isError(etxt_email, false);
						if(mobile.length()>0  && mobile.length() > 9){
							Validations.isError(etxt_contact, false);
							if(pass.length()>0  ){
								Validations.isError(etxt_password, false);
								if(cpass.length()>0  ){
									Validations.isError(etxt_cpassword, false);
									if(pass.equals(cpass) ){
										Validations.isError(etxt_password, false);
										Validations.isError(etxt_cpassword, false);

										Profile profile = new Profile(this);
							            Bitmap bitmap = ((BitmapDrawable) imageview_profile.getDrawable())
												.getBitmap();

										// String picbase64=encodeTobase64(bitmap);

										Map<String, String> params = new HashMap<String, String>();

										params.put(Profile.FIRST_NAME,fname );
										params.put(Profile.LAST_NAME, lname);
										params.put(Profile.EMAIL,email);
										params.put(Profile.MOBILE,mobile);
										params.put(Profile.PASSWORD, pass);
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
											
										
									}else{
										Validations.isError(etxt_cpassword, true);
									}
									
									
								}else{
									Validations.isError(etxt_cpassword, true);
								}
								
								
							}else{
								Validations.isError(etxt_password, true);
							}
							
							
						}else{
							Validations.isError(etxt_contact, true);
						}
						
						
					}else{
						Validations.isError(etxt_email, true);
					}
				
				
				}else{
					Validations.isError(etxt_lname, true);
				}
				
			}else{
				Validations.isError(etxt_fname, true);
			}
			
			
		} else if (v.getId() == R.id.btn_clear) {
			etxt_fname.getText().clear();
			
			etxt_lname.getText().clear();
			etxt_email.getText().clear();
			etxt_contact.getText().clear();
			etxt_password.getText().clear();
			etxt_cpassword.getText().clear();
			Validations.isError(etxt_fname, false);
			Validations.isError(etxt_lname, false);
			Validations.isError(etxt_email, false);
			Validations.isError(etxt_contact, false);
			Validations.isError(etxt_password, false);
			Validations.isError(etxt_cpassword, false);
		}
	}


	public static String encodeTobase64(Bitmap image) {
		Bitmap immagex = image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

		Log.e("LOOK", imageEncoded);
		return imageEncoded;
	}

	public static Bitmap decodeBase64(String input) {
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}
}
