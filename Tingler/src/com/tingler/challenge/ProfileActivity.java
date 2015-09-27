package com.tingler.challenge;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
			etxt_password, etxt_cpassword,etxt_status_msg;
	Profile profile;
	private static final int PICK_CAMERA = 1;
	private static final int PICK_GALLERY = 2;
	private static final int CROP = 3;

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
		imageview_profile.setOnClickListener(this);
		etxt_fname = (EditText) findViewById(R.id.etxt_fname);
		etxt_lname = (EditText) findViewById(R.id.etxt_lname);
		etxt_email = (EditText) findViewById(R.id.etxt_email);

		etxt_contact = (EditText) findViewById(R.id.etxt_contact);
		etxt_password = (EditText) findViewById(R.id.etxt_password);
		etxt_cpassword = (EditText) findViewById(R.id.etxt_cpassword);
		etxt_status_msg = (EditText) findViewById(R.id.etxt_status_msg);
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
			String fname = etxt_fname.getText().toString().trim();
			String lname = etxt_lname.getText().toString().trim();
			String email = etxt_email.getText().toString().trim();
			String mobile = etxt_contact.getText().toString().trim();
			String pass = etxt_password.getText().toString().trim();
			String cpass = etxt_cpassword.getText().toString().trim();
			String status = etxt_status_msg.getText().toString().trim();
			Validations.isError(etxt_fname, false);
			Validations.isError(etxt_lname, false);
			Validations.isError(etxt_email, false);
			Validations.isError(etxt_contact, false);
			Validations.isError(etxt_password, false);
			Validations.isError(etxt_cpassword, false);
			Validations.isError(etxt_status_msg, false);
			if (fname.length() > 0) {
				Validations.isError(etxt_fname, false);
				if (lname.length() > 0) {
					Validations.isError(etxt_lname, false);
					if (email.length() > 0 && Validations.emailValidator(email)) {
						Validations.isError(etxt_email, false);
						if (mobile.length() > 0 && mobile.length() > 9) {
							Validations.isError(etxt_contact, false);
							if (pass.length() > 0) {
								Validations.isError(etxt_password, false);
								if (cpass.length() > 0) {
									Validations.isError(etxt_cpassword, false);
									if (pass.equals(cpass)) {
										Validations.isError(etxt_password,
												false);
										Validations.isError(etxt_cpassword,
												false);
										if (status.length() > 0) {
											Validations.isError(etxt_status_msg, false);
											
											
										Profile profile = new Profile(this);
										Bitmap bitmap = ((BitmapDrawable) imageview_profile
												.getDrawable()).getBitmap();

										// String
										// picbase64=encodeTobase64(bitmap);

										Map<String, String> params = new HashMap<String, String>();

										params.put(Profile.FIRST_NAME, fname);
										params.put(Profile.LAST_NAME, lname);
										params.put(Profile.EMAIL, email);
										params.put(Profile.MOBILE, mobile);
										params.put(Profile.PASSWORD, pass);
										// params.put(Profile.Profile_Img,"profile image");
										params.put(Profile.ID, profile.getId());
										params.put(Profile.STATUS_MSG, "2");
										if (profile.getGoogleId().length() > 0
												|| profile.getFacebookId()
														.length() > 0) {

											params.put(Profile.Media_Type,
													profile.getMediaType()
															.toString().trim());
											if (profile.getMediaType()
													.equalsIgnoreCase(
															"facebook")) {
												params.put(Profile.FACEBOOK_ID,
														profile.getFacebookId()
																.toString()
																.trim());
											} else {
												params.put(Profile.GOOGLE_ID,
														profile.getGoogleId()
																.toString()
																.trim());

											}
											authentication
													.requestSocialMediaAPI(params);
										} else {
											authentication
													.requestProfileAPI(params);
										}
										}else{
											Validations.isError(etxt_status_msg, true);
										}
									} else {
										Validations.isError(etxt_cpassword,
												true);
									}

								} else {
									Validations.isError(etxt_cpassword, true);
								}

							} else {
								Validations.isError(etxt_password, true);
							}

						} else {
							Validations.isError(etxt_contact, true);
						}

					} else {
						Validations.isError(etxt_email, true);
					}

				} else {
					Validations.isError(etxt_lname, true);
				}

			} else {
				Validations.isError(etxt_fname, true);
			}

		} else if (v.getId() == R.id.btn_clear) {
			etxt_fname.getText().clear();

			etxt_lname.getText().clear();
			etxt_email.getText().clear();
			etxt_contact.getText().clear();
			etxt_password.getText().clear();
			etxt_cpassword.getText().clear();
			etxt_status_msg.getText().clear();
			Validations.isError(etxt_fname, false);
			Validations.isError(etxt_lname, false);
			Validations.isError(etxt_email, false);
			Validations.isError(etxt_contact, false);
			Validations.isError(etxt_password, false);
			Validations.isError(etxt_cpassword, false);
			Validations.isError(etxt_status_msg, false);
		} else if (v.getId() == R.id.imageview_profile) {
			registerForContextMenu(imageview_profile);
			openContextMenu(imageview_profile);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Upload profile photo");
		menu.add(0, v.getId(), 0, "Gallery");// groupId, itemId, order, title
		menu.add(0, v.getId(), 0, "Camera");
		menu.add(0, v.getId(), 0, "Cancel");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		if (item.getTitle() == "Gallery") {
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			photoPickerIntent.putExtra("crop", "true");
			photoPickerIntent.putExtra("aspectX", 1);
			photoPickerIntent.putExtra("aspectY", 1);
			photoPickerIntent.putExtra("outputX", 256);
			photoPickerIntent.putExtra("outputY", 256);
			photoPickerIntent.putExtra("return-data", true);
			startActivityForResult(photoPickerIntent, PICK_GALLERY);

		} else if (item.getTitle() == "Camera") {
			 Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			    
			    	startActivityForResult(takePictureIntent, PICK_CAMERA);
			    }
		} else if (item.getTitle() == "Cancel") {
			return false;
		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (PICK_GALLERY == requestCode) {
			try {
				Bundle extras = data.getExtras();
				Bitmap selectedImage = extras.getParcelable("data");
				imageview_profile.setImageBitmap(getCroppedBitmap(selectedImage));
             	} catch (Exception e) {
				Toast.makeText(this, "Please try another image.",
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		} else if (PICK_CAMERA== requestCode) {
			try {
			Bundle extras = data.getExtras();
			Bitmap selectedImage = (Bitmap) extras.get("data");
			imageview_profile.setImageBitmap(getCroppedBitmapCamera(selectedImage));
			} catch (Exception e) {
			
				Toast.makeText(this, "Please try another image.",
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
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

	public Bitmap getCroppedBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);

		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, 100,
				paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
	public Bitmap getCroppedBitmapCamera(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);

		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, 70,
				paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
}
