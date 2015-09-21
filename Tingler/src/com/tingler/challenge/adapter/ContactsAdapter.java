package com.tingler.challenge.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.tingler.challenge.R;
import com.tingler.challenge.util.ContactItem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class ContactsAdapter extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	private ArrayList<ContactItem> contactsArrayList = null;
	private ArrayList<ContactItem> arraylist;

	public ContactsAdapter(Context context,
			ArrayList<ContactItem> worldpopulationlist) {
		mContext = context;
		this.contactsArrayList = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<ContactItem>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder {
		TextView name;
	    TextView mobile;
		//TextView level;
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

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.row_dialog_item, null);
		
			holder.name = (TextView) view.findViewById(R.id.txt_name);
			holder.mobile = (TextView) view.findViewById(R.id.txt_mobile);
		//	holder.level = (TextView) view.findViewById(R.id.txt_level);
			
		//	holder.flag = (ImageView) view.findViewById(R.id.image);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
	
		holder.name.setText(contactsArrayList.get(position).getName());
		holder.mobile.setText(contactsArrayList.get(position).getMobile());
	//	holder.level.setText("level");
		
	//	holder.flag.setImageResource(contactsArrayList.get(position)
	//			.getFlag());
	
		
		return view;
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
