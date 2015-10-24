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
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.Toast;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tingler.challenge.R;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.AppController;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.EditChallenge;
import com.tingler.challenge.util.GetChallengeDetailsItems;
import com.tingler.challenge.util.MembersItems;
import com.tingler.challenge.util.Profile;
import com.tingler.challenge.util.SetterGetter;

public class ChallengePendding extends Fragment implements OnClickListener{
	ArrayList<MembersItems> memberArrayList;
	LinearLayout layout_lv_members, bottomLayout;

	Authentication authentication;
	TextView txt_challengename, txt_challengeDescription, txt_challengetime,
			txt_pize, txt_coins,txt_edit;
	EditText etxt_days, etxt_hours, etxt_minutes;
	GetChallengeDetailsItems getChallengeDetailsItems;
	SlidingDrawer slidingDrawer;

	FrameLayout.LayoutParams layoutParams;
	InputMethodManager imm;
	int height;
	Button btn_start,btn_removeme,btn_stop_notifi;
	Profile profile;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View aboutView=inflater.inflate(R.layout.fragment_challenge_pendding,container,false);
		return init(aboutView) ;
	}
	public View init(View view) {
		profile=new Profile(getActivity());
		layout_lv_members = (LinearLayout) view
				.findViewById(R.id.layout_lv_members);
		bottomLayout = (LinearLayout) view.findViewById(R.id.bottomLayout);
		btn_removeme=(Button)view.findViewById(R.id.btn_removeme);
		btn_stop_notifi=(Button)view.findViewById(R.id.btn_stop_notifi);
		
		btn_start=(Button)view.findViewById(R.id.btn_start);
		btn_start.setOnClickListener(this);
		btn_removeme.setOnClickListener(this);
		btn_stop_notifi.setOnClickListener(this);
		if(SetterGetter.getUserType()!=4){
			btn_start.setVisibility(View.INVISIBLE);
		}
		
		
		slidingDrawer = (SlidingDrawer) view.findViewById(R.id.slidingDrawer);
		txt_challengename = (TextView) view
				.findViewById(R.id.txt_challengename);
		txt_challengeDescription = (TextView) view
				.findViewById(R.id.txt_challengeDescription);
		txt_challengetime = (TextView) view
				.findViewById(R.id.txt_challengetime);
		txt_coins = (TextView) view.findViewById(R.id.txt_coins);
		txt_pize = (TextView) view.findViewById(R.id.txt_pize);
		etxt_days = (EditText) view.findViewById(R.id.etxt_days);
		etxt_hours = (EditText) view.findViewById(R.id.etxt_hours);
		etxt_minutes = (EditText) view.findViewById(R.id.etxt_minutes);
		txt_edit = (TextView) view.findViewById(R.id.txt_edit);
		txt_edit.setOnClickListener(this);
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
	//	slidingDrawer.setLayoutParams(layoutParams);
		slidingDrawer.open();
		bottomLayout.setVisibility(View.INVISIBLE);
		
		
		return view;
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
			//	layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
			//			LayoutParams.MATCH_PARENT);
			//	slidingDrawer.setLayoutParams(layoutParams);
				bottomLayout.setVisibility(View.VISIBLE);
			}
		};
	}
	public void setValue() {
		getChallengeDetailsItems = new GetChallengeDetailsItems();
		txt_challengename.setText(getChallengeDetailsItems.getTitle());
		txt_challengeDescription.setText(getChallengeDetailsItems
				.getDescription());
		etxt_days.setText(getChallengeDetailsItems.getC_day());
		etxt_hours.setText(getChallengeDetailsItems.getC_hrs());
		etxt_minutes.setText(getChallengeDetailsItems.getC_min());
		txt_challengetime.setText(getChallengeDetailsItems.getDate_added());
		txt_pize.setText(getChallengeDetailsItems.getCustom_prize());
		txt_coins.setText(getChallengeDetailsItems.getC_coin());
        if(SetterGetter.getUserType()==4){
        	btn_removeme.setVisibility(View.INVISIBLE);
         }
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
			
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(APIS.Challenge_id, getChallengeDetailsItems.getC_id());
			params.put(APIS.CC_user_id,getChallengeDetailsItems
					.getParticipantItemsArrayList().get(i).getUser_id());
			imv_cross.setTag(params);
			imv_cross.setOnClickListener(this);
			layout_lv_members.addView(rowView);
			if(SetterGetter.getUserType()!=4){
				imv_cross.setVisibility(View.INVISIBLE);
			}else{
				if(getChallengeDetailsItems.getParticipantItemsArrayList().get(i).getUser_id().equalsIgnoreCase(profile.getId())){
					imv_cross.setVisibility(View.INVISIBLE);
				}
			}
			
		}
	}
	@Override
	public void onClick(View v) {
		String user_id=profile.getId();
		authentication=new Authentication(getActivity());
		
		if(v.getId()==R.id.btn_start){
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(APIS.Challenge_id, getChallengeDetailsItems.getC_id());
			params.put(APIS.CC_user_id,user_id);
			System.out.println("input :"+params);
			//	authentication.requestChallengeAcceptAPI(params,fragment);
			}else if(v.getId()==R.id.imv_cross){
				
				HashMap<String, String> params = new HashMap<String, String>();
				params=(HashMap<String, String>) v.getTag();
				authentication.requestChallengeRemoveUserFromChallengeAPI(params, null);
				
			}else if(v.getId()==R.id.txt_edit){
				EditChallenge editChallenge=new EditChallenge(getActivity());
				editChallenge.showDialogBox();
			}else if(v.getId()==R.id.btn_removeme){
				
				HashMap<String, String> params = new HashMap<String, String>();
				params.put(APIS.Challenge_id, getChallengeDetailsItems.getC_id());
				params.put(APIS.CC_user_id, profile.getId());
			    authentication.requestChallengeRemoveUserFromChallengeAPI(params, null);
				
			}else if(v.getId()==R.id.btn_stop_notifi){
				
			}
	
	}
}
