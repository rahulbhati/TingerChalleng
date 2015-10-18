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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;

import com.tingler.challenge.R;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.GetChallengeDetailsItems;
import com.tingler.challenge.util.MembersItems;
import com.tingler.challenge.util.Profile;
import com.tingler.challenge.util.WitnessForVote;

public class Winner extends Fragment implements OnClickListener {
	ArrayList<MembersItems> memberArrayList;
	LinearLayout bottomLayout;

	Authentication authentication;
	TextView txt_challengename, txt_challengeDescription;
	EditText etxt_chat;
	GetChallengeDetailsItems getChallengeDetailsItems;
	SlidingDrawer slidingDrawer;

	FrameLayout.LayoutParams layoutParams;
	InputMethodManager imm;
	int height;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View mermbersView = inflater.inflate(R.layout.fragment_winner,
				container, false);

		return init(mermbersView);
	}

	public View init(View view) {
		slidingDrawer = (SlidingDrawer) view.findViewById(R.id.slidingDrawer);

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
		return view;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if (view.getId() == R.id.btn_sub) {
			// authentication = new Authentication(getActivity());
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
