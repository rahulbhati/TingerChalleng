package com.tingler.challenge.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.tingler.challenge.R;
import com.tingler.challenge.util.ContactItem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class ContactsAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	private ArrayList<ContactItem> contactsArrayList = null;
	private ArrayList<ContactItem> arraylist;
	public ArrayList<Boolean> checkBoxStatus;
	public ContactsAdapter(Context context,
			ArrayList<ContactItem> contactItem) {
		this.context = context;
		this.contactsArrayList = contactItem;
		checkBoxStatus=new ArrayList<Boolean>();
		for(int i=0;contactsArrayList.size()>i;i++){
			checkBoxStatus.add(i, false);
		}
	}

	public class ViewHolder {
		TextView name;
		TextView mobile;
		// TextView level;
		ImageView flag;
	}

	@Override
	public int getCount() {
		return contactsArrayList.size();
	}

	@Override
	public ContactItem getItem(int position) {
		return contactsArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView1, ViewGroup parent) {
	//	final ViewHolder holder;
		 
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View convertView = mInflater.inflate(R.layout.row_dialog_item, null);
		TextView name = (TextView) convertView.findViewById(R.id.txt_name);
		TextView mobile = (TextView) convertView.findViewById(R.id.txt_mobile);
        CheckBox checkBox=(CheckBox)convertView.findViewById(R.id.checkBox);
		name.setText(contactsArrayList.get(position).getName());
		mobile.setText(contactsArrayList.get(position).getMobile());
		checkBox.setChecked(checkBoxStatus.get(position));
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					checkBoxStatus.set(position, isChecked);
				}else{
					checkBoxStatus.set(position, false);
				}
			}
		});
		
		return convertView;
	}

	// Filter Class
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());

		contactsArrayList.clear();
		if (charText.length() == 0) {
			contactsArrayList.addAll(arraylist);
		} else {
			for (ContactItem wp : arraylist) {
				if (wp.getName().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					contactsArrayList.add(wp);
				}
			}
		}
		notifyDataSetChanged();
	}

}
