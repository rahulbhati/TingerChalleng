package com.tingler.challenge.fragment;

import java.util.ArrayList;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.NotificationAdapter;
import com.tingler.challenge.util.NotificationItems;

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
	adapter=new NotificationAdapter(getActivity(), getItem());
	lv_notification.setAdapter(adapter);
}

public ArrayList<NotificationItems> getItem(){
	ArrayList<NotificationItems> arrayList=new ArrayList<NotificationItems>();
	NotificationItems items1=new NotificationItems();
	items1.setTitle("ho! you got new challenge request");
	items1.setHours("5 hours ago...");
	
	NotificationItems items2=new NotificationItems();
	items2.setTitle("Congratulations.");
	items2.setHours("1 hours ago...");
	
	NotificationItems items3=new NotificationItems();
	items3.setTitle("Congratulation... you won!");
	items3.setHours("9 hours ago...");
	
	NotificationItems items4=new NotificationItems();
	items4.setTitle("Time for revenge");
	items4.setHours("1 day ago...");
	
	NotificationItems items5=new NotificationItems();
	items5.setTitle("You got text at you challenge");
	items5.setHours("5 days...");
	
	arrayList.add(items1);
	arrayList.add(items2);
	arrayList.add(items3);
	arrayList.add(items4);
	arrayList.add(items5);
	
	return arrayList;
}

}
