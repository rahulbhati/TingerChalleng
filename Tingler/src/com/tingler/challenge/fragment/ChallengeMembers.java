package com.tingler.challenge.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.ChallengeMembersAdapter;
import com.tingler.challenge.util.MembersItems;

public class ChallengeMembers extends Fragment implements OnClickListener {
	ArrayList<MembersItems> memberArrayList;
	LinearLayout layout_lv_members;
    ImageView imv_uparrow;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View mermbersView = inflater.inflate(
				R.layout.fragment_challenge_members, container, false);

		return init(mermbersView);
	}

	public View init(View view) {
		layout_lv_members = (LinearLayout) view
				.findViewById(R.id.layout_lv_members);
		imv_uparrow=(ImageView)view.findViewById(R.id.imv_uparrow);
		imv_uparrow.setOnClickListener(this);
		memberArrayList = new ArrayList<MembersItems>();
 
		for (int i = 0; i < 5; i++) {
			MembersItems items = new MembersItems();
			items.setName("Item " + i);
			memberArrayList.add(items);
			LayoutInflater mInflater = (LayoutInflater) getActivity()
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			View rowView = mInflater.inflate(R.layout.row_members, null);
            ImageView imv_cross=(ImageView)rowView.findViewById(R.id.imv_cross);
            imv_cross.setTag(i);
            
            imv_cross.setOnClickListener(this);
            
			layout_lv_members.addView(rowView);
			
		}
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.imv_uparrow){
			
		}else if(v.getId()==R.id.imv_cross){
			Toast.makeText(getActivity(), ""+v.getTag(), Toast.LENGTH_LONG).show();
		}
	}
	
	

}
