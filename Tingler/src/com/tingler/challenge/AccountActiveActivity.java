package com.tingler.challenge;

import java.util.HashMap;
import java.util.Map;

import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.Profile;
import com.tingler.challenge.util.Validations;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AccountActiveActivity extends Activity implements OnClickListener {
	TextView txt_accountactive_error;
	Authentication authentication;
	Button btn_account_active;
	private static int ACTIVE_ACCOUNT = 6;
	TextView toolbar_title;
	EditText etxt_otp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accountactive);
		init();
	}

	public void init() {
		authentication = new Authentication(this);
		toolbar_title = (TextView) findViewById(R.id.toolbar_title);

		txt_accountactive_error = (TextView) findViewById(R.id.txt_accountactive_error);
		txt_accountactive_error.setText(getResources().getString(
				R.string.txt_accountactive_error));
		toolbar_title.setText("Active Account");
		btn_account_active = (Button) findViewById(R.id.btn_account_active);
		etxt_otp = (EditText) findViewById(R.id.etxt_otp);

		btn_account_active.setOnClickListener(this);
		Typeface robotoBoldTF = Typeface.createFromAsset(getAssets(),"fonts/roboto_bold.ttf"); 
		toolbar_title.setTypeface(robotoBoldTF);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String opt = etxt_otp.getText().toString().trim();
		if (opt.length() > 0) {
			Validations.isError(etxt_otp, false);
			Profile profile = new Profile(this);
			Map<String, String> params = new HashMap<String, String>();
			params.put(APIS.MOBILT, profile.getMobile());
			params.put(APIS.Is_verify, opt);

			authentication.requestActiveAccountAPI(params);
		} else {
			Validations.isError(etxt_otp, true);
		}
	}
}
