package com.tingler.challenge.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;

import com.tingler.challenge.MainActivity;
import com.tingler.challenge.R;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.fragment.createchallenge.Details;
import com.tingler.challenge.util.GetChallengeDetailsItems;
import com.tingler.challenge.util.MembersItems;
import com.tingler.challenge.util.Profile;
import com.tingler.challenge.util.WitnessForVote;

public class SelectWinner extends Fragment implements OnClickListener {
	ArrayList<MembersItems> memberArrayList;
	LinearLayout layout_members, bottomLayout;

	Authentication authentication;
	TextView txt_challengename, txt_challengeDescription;
	EditText etxt_chat;
	GetChallengeDetailsItems getChallengeDetailsItems;
	SlidingDrawer slidingDrawer;

	FrameLayout.LayoutParams layoutParams;
	InputMethodManager imm;
	int height;
     Button btn_sub;
	ArrayList<WitnessForVote> selectwinnerArrayList = null;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View mermbersView = inflater.inflate(R.layout.fragment_select_winer,
				container, false);

		return init(mermbersView);
	}

	public View init(View view) {
		layout_members = (LinearLayout) view
				.findViewById(R.id.layout_lv_members);
        btn_sub=(Button)view.findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener(this);
		slidingDrawer = (SlidingDrawer) view.findViewById(R.id.slidingDrawer);
		txt_challengename = (TextView) view
				.findViewById(R.id.txt_challengename);
		txt_challengeDescription = (TextView) view
				.findViewById(R.id.txt_challengeDescription);
		bottomLayout = (LinearLayout) view.findViewById(R.id.bottomLayout);
		etxt_chat = (EditText) view.findViewById(R.id.etxt_chat);

		slidingDrawer.setOnDrawerOpenListener(openDrawer());
		slidingDrawer.setOnDrawerCloseListener(closeDrawer());
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		height = size.y;
		imm = (InputMethodManager) getActivity().getSystemService(
				Context.INPUT_METHOD_SERVICE);

		layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				height / 2);
		slidingDrawer.setLayoutParams(layoutParams);
		slidingDrawer.open();
		bottomLayout.setVisibility(View.VISIBLE);
		
		etxt_chat.setOnFocusChangeListener(focus());
		selectwinnerArrayList = new ArrayList<WitnessForVote>();
		
		for (int i = 0; i < 5; i++) {

			WitnessForVote vote = new WitnessForVote();
			vote.setName("Item :" + i);
			selectwinnerArrayList.add(vote);
			LayoutInflater mInflater = (LayoutInflater) getActivity()
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			View rowview = mInflater.inflate(R.layout.row_select_winner, null);
			LinearLayout layout = (LinearLayout) rowview
					.findViewById(R.id.layout);
			layout.setOnClickListener(this);
			
			layout.setTag(i);
			layout_members.addView(rowview);
		} 
		return view;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if (view.getId() == R.id.btn_sub) {
		//	authentication = new Authentication(getActivity());
			MainActivity.toolbar_title.setText("Challenge Winner");
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new Winner()).commit();
			

		} else {

			for (int i = 0; i < 5; i++) {
				View relative = layout_members.getChildAt(i);
				LinearLayout v = (LinearLayout) relative
						.findViewById(R.id.layout);
				if (view.getTag().equals(v.getTag())) {
					v.setBackgroundResource(R.drawable.border_blue);
					TextView txt_name = (TextView) v
							.findViewById(R.id.txt_name);
					TextView txt_witness_option = (TextView) v
							.findViewById(R.id.txt_witness_option);
					txt_name.setTextColor(getActivity().getResources()
							.getColor(R.color.darkBlue_txt));
					txt_witness_option.setTextColor(getActivity()
							.getResources().getColor(R.color.white_txt));
					RadioButton radioBtn = (RadioButton) v
							.findViewById(R.id.radioBtn);
					radioBtn.setChecked(true);
				} else {
					v.setBackgroundResource(R.drawable.border_gray);
					TextView txt_name = (TextView) v
							.findViewById(R.id.txt_name);
					TextView txt_witness_option = (TextView) v
							.findViewById(R.id.txt_witness_option);
					txt_name.setTextColor(getActivity().getResources()
							.getColor(R.color.black_txt));
					txt_witness_option.setTextColor(getActivity()
							.getResources().getColor(R.color.black_txt));
					RadioButton radioBtn = (RadioButton) v
							.findViewById(R.id.radioBtn);
					radioBtn.setChecked(false);
				}

			}
		}
	}

	public OnDrawerOpenListener openDrawer() {
		return new OnDrawerOpenListener() {

			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				bottomLayout.setVisibility(View.INVISIBLE);
			}
		};
	}

	public OnDrawerCloseListener closeDrawer() {
		return new OnDrawerCloseListener() {

			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				layoutParams = new FrameLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				slidingDrawer.setLayoutParams(layoutParams);
				bottomLayout.setVisibility(View.VISIBLE);
			}
		};
	}

	public OnFocusChangeListener focus() {
		return new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus) {

					slidingDrawer.close();
				} else {

				}
			}
		};
	}
}
