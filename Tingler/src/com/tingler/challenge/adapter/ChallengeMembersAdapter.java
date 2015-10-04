package com.tingler.challenge.adapter;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tingler.challenge.R;
import com.tingler.challenge.util.MembersItems;
import com.tingler.challenge.util.NotificationItems;

public class ChallengeMembersAdapter extends BaseAdapter {
	ArrayList<MembersItems> memberArrayList;
	Context context;

	public ChallengeMembersAdapter(Context context,
			ArrayList<MembersItems> memberArrayList) {
		this.context = context;
		this.memberArrayList = memberArrayList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return memberArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return memberArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = mInflater.inflate(R.layout.row_members, null);
	

	
		return view;
	}

}
