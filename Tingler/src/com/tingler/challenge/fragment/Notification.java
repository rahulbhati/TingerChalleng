package com.tingler.challenge.fragment;

import java.util.ArrayList;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.NotificationAdapter;
import com.tingler.challenge.util.NotificationItems;
import com.tingler.challenge.util.NotificationSetterGetter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Notification  extends Fragment{
ListView lv_notification;
NotificationAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View notificationView=inflater.inflate(R.layout.fragment_notification,container,false);
		init(notificationView);
		return notificationView;
	}
public void init(View view){
	lv_notification=(ListView)view.findViewById(R.id.lv_notification);
	adapter=new NotificationAdapter(getActivity(), NotificationSetterGetter.getArrayList());
	lv_notification.setAdapter(adapter);
}


}
