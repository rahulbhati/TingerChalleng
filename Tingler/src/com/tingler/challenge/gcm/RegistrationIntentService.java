package com.tingler.challenge.gcm;

import java.io.IOException;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.tingler.challenge.R;
import com.tingler.challenge.util.Profile;

public class RegistrationIntentService extends IntentService {
	private static final String TAG = "RegIntentService";
	String token;

	public RegistrationIntentService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		InstanceID instanceID = InstanceID.getInstance(this);
		String senderId = getResources()
				.getString(R.string.gcm_defaultSenderId);
		try {
                  token = instanceID.getToken(senderId,
					GoogleCloudMessaging.INSTANCE_ID_SCOPE);
			Log.d(TAG, "GCM Registration Token: " + token);
		
		} catch (IOException e) {
			Log.d(TAG, "Failed to complete token refresh", e);
			e.printStackTrace();
		}
		Profile profile = new Profile(this);
		profile.setDeviceTokenGcm(token);
	}

}
