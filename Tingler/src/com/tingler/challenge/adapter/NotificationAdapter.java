package com.tingler.challenge.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tingler.challenge.util.NotificationItems;

public class NotificationAdapter extends BaseAdapter {
	ArrayList<NotificationItems> notificationArrayList;
	Context context;

	public NotificationAdapter(Context context,
			ArrayList<NotificationItems> notificationArrayList) {
		this.context = context;
		this.notificationArrayList = notificationArrayList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return notificationArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return notificationArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(
					com.tingler.challenge.R.layout.notificaiton_item, null);
		}

		TextView txt_title = (TextView) convertView
				.findViewById(com.tingler.challenge.R.id.txt_title);
		TextView txt_time = (TextView) convertView
				.findViewById(com.tingler.challenge.R.id.txt_time);

		txt_title.setText(notificationArrayList.get(position).getTitle());
		txt_time.setText(notificationArrayList.get(position).getHours());
		return convertView;
	}

}
