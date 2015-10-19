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

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tingler.challenge.MainActivity;
import com.tingler.challenge.R;
import com.tingler.challenge.adapter.VoteForWitnessAdapter;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.AppController;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.fragment.createchallenge.Details;
import com.tingler.challenge.util.GetChallengeDetailsItems;
import com.tingler.challenge.util.MembersItems;
import com.tingler.challenge.util.Profile;
import com.tingler.challenge.util.VoteForWitnessSetterGetter;
import com.tingler.challenge.util.WitnessForVote;

public class SelectWinner extends Fragment implements OnClickListener {
	Button btn_submit;

	
	ArrayList<WitnessForVote> selectforwinnerArrayList = null;
	LinearLayout layout_members;
	Authentication authentication;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View mermbersView = inflater.inflate(R.layout.fragment_select_winer,
				container, false);

		return init(mermbersView);
	}

	public View init(View view) {
		
		btn_submit = (Button) view.findViewById(R.id.btn_submit);
		layout_members = (LinearLayout) view.findViewById(R.id.layout_members);

		btn_submit.setOnClickListener(this);

		for (int i = 0; i < 5; i++) {

			LayoutInflater mInflater = (LayoutInflater) getActivity()
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			View rowview = mInflater.inflate(R.layout.row_witness_vote, null);
			NetworkImageView imv_icon = (NetworkImageView) rowview
					.findViewById(R.id.imv_icon);
			ImageLoader mImageLoader = null;
			mImageLoader = AppController.getInstance().getImageLoader();
			imv_icon.setImageUrl("http://mycruisecareer.com/wp-content/uploads/2014/03/noelle-profile-pic-in-circle-200x200.png", mImageLoader);

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
		if (view.getId() == R.id.btn_submit) {
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


	
}
