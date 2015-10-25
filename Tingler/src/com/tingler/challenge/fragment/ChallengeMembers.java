package com.tingler.challenge.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Fragment;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tingler.challenge.R;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.AppController;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.GetChallengeDetailsItems;
import com.tingler.challenge.util.MembersItems;
import com.tingler.challenge.util.Profile;

public class ChallengeMembers extends Fragment implements OnClickListener {
	ArrayList<MembersItems> memberArrayList;
	LinearLayout layout_lv_members, bottomLayout;
    Authentication authentication;
	TextView txt_challengename, txt_challengeDescription, txt_challengetime,
			txt_pize, txt_coins;
	EditText etxt_days, etxt_hours, etxt_minutes, etxt_chat;
	GetChallengeDetailsItems getChallengeDetailsItems;
	SlidingDrawer slidingDrawer;
    FrameLayout.LayoutParams layoutParams;
	InputMethodManager imm;
	int height;
	ImageView imv_updown;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View mermbersView = inflater.inflate(
				R.layout.fragment_challenge_members, container, false);

		return init(mermbersView);
	}

	public View init(View view) {
		getChallengeDetailsItems = new GetChallengeDetailsItems();
		layout_lv_members = (LinearLayout) view
				.findViewById(R.id.layout_lv_members);
		bottomLayout = (LinearLayout) view.findViewById(R.id.bottomLayout);
		slidingDrawer = (SlidingDrawer) view.findViewById(R.id.slidingDrawer);
		txt_challengename = (TextView) view
				.findViewById(R.id.txt_challengename);
		txt_challengeDescription = (TextView) view
				.findViewById(R.id.txt_challengeDescription);
		txt_challengetime = (TextView) view
				.findViewById(R.id.txt_challengetime);
		txt_coins = (TextView) view.findViewById(R.id.txt_coins);
		imv_updown=(ImageView)view.findViewById(R.id.imv_updown);
		txt_pize = (TextView) view.findViewById(R.id.txt_pize);
		etxt_days = (EditText) view.findViewById(R.id.etxt_days);
		etxt_hours = (EditText) view.findViewById(R.id.etxt_hours);
		etxt_minutes = (EditText) view.findViewById(R.id.etxt_minutes);
		etxt_chat = (EditText) view.findViewById(R.id.etxt_chat);

		etxt_days.setEnabled(false);
		etxt_hours.setEnabled(false);
		etxt_minutes.setEnabled(false);

		setValue();
		slidingDrawer.setOnDrawerOpenListener(openDrawer());
		slidingDrawer.setOnDrawerCloseListener(closeDrawer());
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		height = size.y;
		imm = (InputMethodManager) getActivity().getSystemService(
				Context.INPUT_METHOD_SERVICE);

	//	layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
	//			height / 2);
//		slidingDrawer.setLayoutParams(layoutParams);
		slidingDrawer.open();
		bottomLayout.setVisibility(View.INVISIBLE);

		etxt_chat.setOnFocusChangeListener(focus());

		return view;
	}

	public void setValue() {

		txt_challengename.setText(getChallengeDetailsItems.getTitle());
		txt_challengeDescription.setText(getChallengeDetailsItems
				.getDescription());
		etxt_days.setText(getChallengeDetailsItems.getC_day());
		etxt_hours.setText(getChallengeDetailsItems.getC_hrs());
		etxt_minutes.setText(getChallengeDetailsItems.getC_min());
		txt_challengetime.setText(getChallengeDetailsItems.getDate_added());
		txt_pize.setText(getChallengeDetailsItems.getCustom_prize());
		txt_coins.setText(getChallengeDetailsItems.getC_coin());

		for (int i = 0; i < getChallengeDetailsItems
				.getParticipantItemsArrayList().size(); i++) {

			LayoutInflater mInflater = (LayoutInflater) getActivity()
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			View rowView = mInflater.inflate(R.layout.row_members, null);
			ImageView imv_cross = (ImageView) rowView
					.findViewById(R.id.imv_cross);
			ImageView imv_circle = (ImageView) rowView
					.findViewById(R.id.imv_circle);
			TextView txt_name = (TextView) rowView.findViewById(R.id.txt_name);
			TextView txt_challengee_witness = (TextView) rowView
					.findViewById(R.id.txt_challengee_witness);

			txt_name.setText(getChallengeDetailsItems
					.getParticipantItemsArrayList().get(i).getName());
			txt_challengee_witness.setText(getChallengeDetailsItems
					.getParticipantItemsArrayList().get(i).getUser_type());

			if (getChallengeDetailsItems.getParticipantItemsArrayList().get(i)
					.getC_status().equalsIgnoreCase("0")) {
				imv_circle.setImageResource(R.drawable.yallow_circle);
			} else {
				imv_circle.setImageResource(R.drawable.green_circle);
			}

			NetworkImageView imv_icon = (NetworkImageView) rowView
					.findViewById(R.id.imv_icon);
			ImageLoader mImageLoader = null;
			mImageLoader = AppController.getInstance().getImageLoader();

			imv_icon.setImageUrl(getChallengeDetailsItems
					.getParticipantItemsArrayList().get(i).getProfile_img(),
					mImageLoader);
			imv_cross.setTag(getChallengeDetailsItems
					.getParticipantItemsArrayList().get(i).getUser_id());

			imv_cross.setOnClickListener(this);

			layout_lv_members.addView(rowView);

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.imv_cross) {
			// Toast.makeText(getActivity(), "" + v.getTag(), Toast.LENGTH_LONG)
			// .show();
			Profile profile = new Profile(getActivity());
			String user_id = profile.getId();
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(APIS.Challenge_id,
					getChallengeDetailsItems.getChallenger_id());
			params.put(APIS.CC_user_id, user_id);
			System.out.println("input :" + params);
			// authentication.requestChallengeRemoveUserFromChallengeAPI(params);
		}
	}

	public OnDrawerOpenListener openDrawer() {
		return new OnDrawerOpenListener() {

			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				bottomLayout.setVisibility(View.INVISIBLE);
				imv_updown.setImageResource(R.drawable.slide_up);
			}
		};
	}

	public OnDrawerCloseListener closeDrawer() {
		return new OnDrawerCloseListener() {

			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				bottomLayout.setVisibility(View.VISIBLE);
				imv_updown.setImageResource(R.drawable.slide_down);
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
