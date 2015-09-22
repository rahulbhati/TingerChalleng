package com.tingler.challenge.adapter;

import java.util.ArrayList;

import com.tingler.challenge.R;
import com.tingler.challenge.util.ContactItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChallengeeAdapter extends BaseAdapter {

	Context context;

	ArrayList<ContactItem> contactsArrayList = null;

	public ChallengeeAdapter(Context context, ArrayList<ContactItem> contactItem) {
		this.context = context;
		this.contactsArrayList = contactItem;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contactsArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return contactsArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView1, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View convertView = mInflater.inflate(R.layout.row_challengee, null);
		TextView name = (TextView) convertView.findViewById(R.id.txt_name);
		TextView mobile = (TextView) convertView.findViewById(R.id.txt_mobile);
		name.setText(contactsArrayList.get(position).getName().toString());
		mobile.setText(contactsArrayList.get(position).getMobile().toString());
		
		return convertView;
	}

}
