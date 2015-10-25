package com.tingler.challenge.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tingler.challenge.MainActivity;
import com.tingler.challenge.R;
import com.tingler.challenge.adapter.VoteForWitnessAdapter;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.AppController;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.DashboardTabSetterGetter;
import com.tingler.challenge.util.GetChallengeDetailsItems;
import com.tingler.challenge.util.Profile;
import com.tingler.challenge.util.ProfileMemberItems;
import com.tingler.challenge.util.VoteForWitnessSetterGetter;
import com.tingler.challenge.util.WitnessForVote;

public class VoteForWitness extends Fragment implements OnClickListener {
	Button btn_submit;

	VoteForWitnessAdapter votforwitnessAdapter;
	ArrayList<WitnessForVote> witnessForVoteArrayList = null;
	LinearLayout layout_members;
	Authentication authentication;
	GetChallengeDetailsItems getChallengeDetailsItems;
    String witness_id=null;
	public VoteForWitness() {
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View witnesssView = inflater.inflate(R.layout.fragment_voteforwitness,
				container, false);
		init(witnesssView);
		return witnesssView;
	}

	public void init(View view) {
		this.witnessForVoteArrayList = VoteForWitnessSetterGetter
				.getVoteForWitnessArrayList();
		btn_submit = (Button) view.findViewById(R.id.btn_submit);
		layout_members = (LinearLayout) view.findViewById(R.id.layout_members);

		btn_submit.setOnClickListener(this);

		for (int i = 0; i < witnessForVoteArrayList.size(); i++) {

			LayoutInflater mInflater = (LayoutInflater) getActivity()
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			View rowview = mInflater.inflate(R.layout.row_witness_vote, null);
			TextView txt_name = (TextView) rowview.findViewById(R.id.txt_name);
			txt_name.setText(witnessForVoteArrayList.get(i).getName());
			NetworkImageView imv_icon = (NetworkImageView) rowview
					.findViewById(R.id.imv_icon);
			ImageLoader mImageLoader = null;
			mImageLoader = AppController.getInstance().getImageLoader();
		
			//	imv_icon.setImageUrl(witnessForVoteArrayList.get(i)
			//			.getProfile_img(), mImageLoader);
			
			

			LinearLayout layout = (LinearLayout) rowview
					.findViewById(R.id.layout);
			layout.setOnClickListener(this);

			layout.setTag(i);

			layout_members.addView(rowview);
		}

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		if (view.getId() == R.id.btn_submit) {
			authentication = new Authentication(getActivity());
			getChallengeDetailsItems = new GetChallengeDetailsItems();
			Profile profile = new Profile(getActivity());
			String user_id = profile.getId();
			if(witness_id!=null && witness_id.length()>0){
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(APIS.Challenge_id,
					getChallengeDetailsItems.getC_id());
			params.put(APIS.CC_user_id, user_id);
			params.put(APIS.Witness_id, witness_id);
			System.out.println("vote input :" + params);
			Toast.makeText(getActivity(),""+ params, Toast.LENGTH_LONG).show();
			//authentication.requestChallengeVoteForWitnessAPI(params);
			}else{
				Toast.makeText(getActivity(), "Please select witness", Toast.LENGTH_LONG).show();
			}
			
			} else {

			for (int i = 0; i < witnessForVoteArrayList.size(); i++) {
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
					witness_id=witnessForVoteArrayList.get(i).getUser_id();
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
