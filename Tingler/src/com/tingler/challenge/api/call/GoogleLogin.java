package com.tingler.challenge.api.call;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.tingler.challenge.ProfileActivity;
import com.tingler.challenge.util.Profile;

public class GoogleLogin implements ConnectionCallbacks,
		OnConnectionFailedListener {
	private static int GOOGLE_LOGIN_TAG = 0;
	public static GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    Context context;
	private ConnectionResult mConnectionResult;

	public GoogleLogin(Context context) {
		this.context = context;
	}

	public void googleLogin() {
		mGoogleApiClient = new GoogleApiClient.Builder(context)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this).addApi(Plus.API)
				.addScope(Plus.SCOPE_PLUS_LOGIN).build();

		mGoogleApiClient.connect();
		mIntentInProgress = false;
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		if (!mIntentInProgress && result.hasResolution()) {
			try {
				mIntentInProgress = true;
				mConnectionResult = result;
				mConnectionResult.startResolutionForResult((Activity) context,
						GOOGLE_LOGIN_TAG);
			} catch (SendIntentException e) {
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
	
		HashMap<String, String> profileHashMap = new HashMap<String, String>();
		profileHashMap = getProfileDetails();
        Profile profile=new Profile(context);
        profile.setFirstName(profileHashMap.get(Profile.FIRST_NAME));
        profile.setLastName(profileHashMap.get(Profile.LAST_NAME));
        //profile.set(profileHashMap.get(Profile.Profile_Img));
        profile.setEmail(profileHashMap.get(Profile.EMAIL));
        profile.setGoogleId(profileHashMap.get(APIS.Gplus_id));
        profile.setMediaType("google_plus");
       
        Intent intent=new Intent(context,ProfileActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		mGoogleApiClient.connect();
	}

	public void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		// TODO Auto-generated method stub
		if (responseCode != 0) {
			if (requestCode == GOOGLE_LOGIN_TAG) {
				mIntentInProgress = false;

				if (!mGoogleApiClient.isConnecting()) {
					mGoogleApiClient.connect();
				}
			}
		} else {
			googleLogout();
			mIntentInProgress = true;
		}
	}

	public HashMap<String, String> getProfileDetails() {

		HashMap<String, String> profileHashMap = new HashMap<String, String>();

		try {
			if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
				Person currentPerson = Plus.PeopleApi
						.getCurrentPerson(mGoogleApiClient);
				System.out.println("Curent Person :" + currentPerson);
				//String name = currentPerson.getDisplayName();
				String photoUrl = currentPerson.getImage().getUrl();
				String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
				
				profileHashMap.put(Profile.FIRST_NAME, currentPerson.getName().getGivenName());
				profileHashMap.put(Profile.LAST_NAME, currentPerson.getName().getFamilyName());
			    profileHashMap.put(Profile.Profile_Img,photoUrl);
				profileHashMap.put(Profile.EMAIL, email);
				profileHashMap.put(APIS.Gplus_id, currentPerson.getId());
				profileHashMap.put(APIS.Media_type, "google_plus");
			} else {
				Toast.makeText(context, "Person information is null",
						Toast.LENGTH_LONG).show();
				googleLogout();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profileHashMap;
	}

	public void googleLogout() {
		if (mGoogleApiClient.isConnected()) {
			Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
			mGoogleApiClient.disconnect();

		}
	}

}
