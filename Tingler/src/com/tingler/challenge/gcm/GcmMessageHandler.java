package com.tingler.challenge.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;
import com.tingler.challenge.MainActivity;
import com.tingler.challenge.R;

public class GcmMessageHandler extends GcmListenerService {
    public static final int MESSAGE_NOTIFICATION_ID = 435345;

    @Override
    public void onMessageReceived(String from, Bundle data) {
    
    	System.out.println("bundle :"+data);
    	String alert=data.getString("alert");
        createNotification(from, alert);
    }

        // Creates notification based on title and body received
    private void createNotification(String title, String alert) {
    	
        Context context = getBaseContext();
    	System.out.println("Notification receive");
    	
        Intent myIntent = new Intent(context, MainActivity.class);
        myIntent.putExtra("notification", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(
               context, 
               0, 
               myIntent, 
               Intent.FLAG_ACTIVITY_NEW_TASK); 
        
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.icon_notification).setContentTitle("Tingler")
                .setContentText(alert);
        mBuilder.setContentIntent(pendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
        
    }

}
