package com.tingler.challenge.api.call;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionDefaultAudience;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.facebook.internal.SessionTracker;
import com.facebook.model.GraphUser;
import com.tingler.challenge.ProfileActivity;
import com.tingler.challenge.util.Profile;

public class FacebookLogin {
	private Facebook mFacebook;
	public static Session mCurrentSession;
	public static SessionTracker mSessionTracker;
	public static String email, dob = "", url = "", id = "",imageurl="",
			name = "";
	private AsyncFacebookRunner mAsyncRunner;
	
	String applicationId="1063645003655210";
	
	@SuppressWarnings("deprecation")
	public void signInWithFacebook(final Context context) {
		mFacebook = new Facebook(applicationId);
		mAsyncRunner = new AsyncFacebookRunner(mFacebook);
		// creating hash key for facebook
		String hashkey = printKeyHash(context);
		Log.e("Hash Key", hashkey);
		mSessionTracker = new SessionTracker(context, new StatusCallback() {
			@Override
			public void call(Session session, SessionState state,
					Exception exception) {
			}
		}, null, false);
	//	String applicationId = appID;
		mCurrentSession = mSessionTracker.getSession();
		if (mCurrentSession == null || mCurrentSession.getState().isClosed()) {
			mSessionTracker.setSession(null);
			Session session = new Session.Builder(context).setApplicationId(
					applicationId).build();
			Session.setActiveSession(session);
			mCurrentSession = session;
		}
		if (!mCurrentSession.isOpened()) {
			Session.OpenRequest openRequest = null;
			openRequest = new Session.OpenRequest((Activity) context);
			if (openRequest != null) {
				openRequest.setDefaultAudience(SessionDefaultAudience.FRIENDS);
				openRequest.setPermissions(Arrays.asList("public_profile","user_birthday",
						"email", "user_location"));
				openRequest
						.setLoginBehavior(SessionLoginBehavior.SSO_WITH_FALLBACK);
				mCurrentSession.openForRead(openRequest);
			}
		} else {
			Request.executeMeRequestAsync(mCurrentSession,
					new Request.GraphUserCallback() {
						@Override
						public void onCompleted(GraphUser user,
								Response response) {
							Log.w("myConsultant",
									user.getId() + " " + user.getFirstName()+" "+user.getLastName() + " "
											+ user.getInnerJSONObject());

							String json = user.getInnerJSONObject().toString();
							try {
								
								JSONObject profile = new JSONObject(json);
								System.out.println("Facebook :"+profile);
								name = profile.getString("name");
								if(profile.has("email"))
								email = profile.getString("email");
								//fname = profile.getString("first_name");
							//	lname = profile.getString("last_name");
								id = profile.getString("id");
								imageurl="https://graph.facebook.com/"+id+"/picture?width=150&height=150";
								System.out.println("signInWithFacebook :"+profile.toString());
							} catch (JSONException e) {
								e.printStackTrace();
								System.out.println(e.getMessage());
							}

						}
					});
		}
	}

	public static String printKeyHash(Context context) {
		PackageInfo packageInfo;
		String key = null;
		try {

			// getting application package name, as defined in manifest
			String packageName = context.getApplicationContext()
					.getPackageName();

			// Retriving package info
			packageInfo = context.getPackageManager().getPackageInfo(
					packageName, PackageManager.GET_SIGNATURES);

			Log.e("Package Name=", context.getApplicationContext()
					.getPackageName());

			for (Signature signature : packageInfo.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				key = new String(Base64.encode(md.digest(), 0));

				// String key = new String(Base64.encodeBytes(md.digest()));
				Log.e("Key Hash=", key);

			}
		} catch (NameNotFoundException e1) {
			Log.e("Name not found", e1.toString());
		}

		catch (NoSuchAlgorithmException e) {
			Log.e("No such an algorithm", e.toString());
		} catch (Exception e) {
			Log.e("Exception", e.toString());
		}

		return key;
	}

	public static void callFacebookLogout(Context context) {
		Session session = Session.getActiveSession();
		if (session != null) {

			if (!session.isClosed()) {
				session.closeAndClearTokenInformation();
				// clear your preferences if saved
			}
		} else {

			session = new Session(context);
			Session.setActiveSession(session);

			session.closeAndClearTokenInformation();
			// clear your preferences if saved

		}

	}

	@SuppressWarnings("deprecation")
	public void callingFBResult(final Context context) {
		// TODO Auto-generated method stub
		if (mCurrentSession.isOpened()) {
			Request.executeMeRequestAsync(mCurrentSession,
					new Request.GraphUserCallback() {


						@Override
						public void onCompleted(GraphUser user,
								Response response) {
							Log.w("myConsultant",
									user.getId() + " " + user.getName() + " "
											+ user.getInnerJSONObject());

							
							String json = user.getInnerJSONObject().toString();
							try {
								
								JSONObject profile = new JSONObject(json);
								name = profile.getString("name");
								if(profile.has("email")){
									email = profile.getString("email");	
								}else{
									email=null;
								}
							//	fname = profile.getString("first_name");
							//	lname = profile.getString("last_name");
								id = profile.getString("id");
								imageurl="https://graph.facebook.com/"+id+"/picture?width=150&height=150";
								System.out.println("callingFBResult :"+profile.toString());
								HashMap<String, String> profileHashMap = new HashMap<String, String>();
								
						        Profile fbprofile=new Profile(context);
						        
						        
						        if(name.split(" ")[0].length()>0){
						        	 fbprofile.setFirstName(name.split(" ")[0]);
						        } if(name.split(" ")[1].length()>0){
						        	 fbprofile.setLastName(name.split(" ")[1]);
						        }if(email!=null){
						        	 fbprofile.setEmail(email);
						        }
						        
						        fbprofile.setFacebookId(id);
						        fbprofile.setMediaType("facebook");
						       
						        Intent intent=new Intent(context,ProfileActivity.class);
						        context.startActivity(intent);
						        ((Activity)context).finish();
							
							
							
							
							
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					});
			
			
			
			
			
		}
	}
}
