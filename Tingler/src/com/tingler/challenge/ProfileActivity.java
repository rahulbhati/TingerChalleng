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

public class ProfileActivity extends Activity implements OnClickListener{

	Authentication authentication;
	Button btn_account_active;
	private static int ACTIVE_ACCOUNT =6;
	TextView toolbar_title;
	Button btn_clear, btn_save;
	ImageView imageview_profile;
	public static EditText etxt_fname, etxt_lname,etxt_email,etxt_contact,etxt_password,etxt_cpassword;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_profile);
	init();
}
public void init() {
	authentication = new Authentication(this);
	toolbar_title = (TextView) findViewById(R.id.toolbar_title);
	toolbar_title.setText("Active Account");
	btn_clear = (Button) findViewById(R.id.btn_clear);
	btn_save = (Button) findViewById(R.id.btn_save);
	btn_save.setOnClickListener(this);
	btn_clear.setOnClickListener(this);
	imageview_profile=(ImageView)findViewById(R.id.imageview_profile);
	
	etxt_fname = (EditText) findViewById(R.id.etxt_fname);
	etxt_lname = (EditText) findViewById(R.id.etxt_lname);
	etxt_email = (EditText) findViewById(R.id.etxt_email);
	
	etxt_contact = (EditText) findViewById(R.id.etxt_contact);
	etxt_password = (EditText) findViewById(R.id.etxt_password);
	etxt_cpassword = (EditText) findViewById(R.id.etxt_cpassword);
	
}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	if(v.getId()==R.id.btn_save){
		
		Bitmap bitmap = ((BitmapDrawable)imageview_profile.getDrawable()).getBitmap();
		
		String picbase64=encodeTobase64(bitmap);
		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put(Profile.FIRST_NAME, etxt_fname.getText().toString()
				.trim());
		params.put(Profile.LAST_NAME, etxt_lname.getText().toString()
				.trim());
		params.put(Profile.EMAIL, etxt_email.getText().toString()
				.trim());
		params.put(Profile.MOBILE, etxt_contact.getText().toString()
				.trim());
		params.put(Profile.PASSWORD, etxt_password.getText().toString()
				.trim());
		params.put(Profile.PICTURE,"profile image");
		params.put(Profile.ID,"1");
		params.put(Profile.STATUS_MSG,"hello bro");
		authentication.requestProfileAPI(params);
	
	}else if(v.getId()==R.id.btn_clear){
		
	}
}
public static String encodeTobase64(Bitmap image)
{
    Bitmap immagex=image;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
    immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    byte[] b = baos.toByteArray();
    String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);

    Log.e("LOOK", imageEncoded);
    return imageEncoded;
}
public static Bitmap decodeBase64(String input) 
{
    byte[] decodedByte = Base64.decode(input, 0);
    return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
}
}
