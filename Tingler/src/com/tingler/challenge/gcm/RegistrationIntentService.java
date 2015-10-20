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

public class RegistrationIntentService extends IntentService {
	  public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
	  public static final String GCM_TOKEN = "gcmToken";

    // abbreviated tag name
    private static final String TAG = "RegIntentService";
    String token;
    public RegistrationIntentService() {
        super(TAG);
    } 

    @Override
    protected void onHandleIntent(Intent intent) {
    	
    	  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


    	
        // Make a call to Instance API
        InstanceID instanceID = InstanceID.getInstance(this);
        String senderId = getResources().getString(R.string.gcm_defaultSenderId);
        try {
       
            token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            Log.d(TAG, "GCM Registration Token: " + token);
            // pass along this data

        } catch (IOException e) {
        	  Log.d(TAG, "Failed to complete token refresh", e);
              // If an exception happens while fetching the new token or updating our registration data
              // on a third-party server, this ensures that we'll attempt the update at a later time.
          

            e.printStackTrace();
        }
        // save token
        sharedPreferences.edit().putString(GCM_TOKEN, token).apply();
        // pass along this data
        

    }

}
