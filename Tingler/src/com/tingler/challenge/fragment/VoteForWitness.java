package com.tingler.challenge.fragment;

import java.util.ArrayList;

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

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.VoteForWitnessAdapter;
import com.tingler.challenge.util.WitnessForVote;

public class VoteForWitness extends Fragment implements OnClickListener {
	Button btn_next;

	VoteForWitnessAdapter votforwitnessAdapter;
	ArrayList<WitnessForVote> witnessForVoteArrayList = null;
	LinearLayout layout_members;
 
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

		btn_next = (Button) view.findViewById(R.id.btn_next);
		layout_members = (LinearLayout) view.findViewById(R.id.layout_members);

		btn_next.setOnClickListener(this);
		witnessForVoteArrayList = new ArrayList<WitnessForVote>();
		
		for (int i = 0; i < 5; i++) {

			WitnessForVote vote = new WitnessForVote();
			vote.setName("Item :" + i);
			witnessForVoteArrayList.add(vote);
			LayoutInflater mInflater = (LayoutInflater) getActivity()
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			View rowview = mInflater.inflate(R.layout.row_witness_vote, null);
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
		
	for (int i = 0; i < 5; i++) {
			View relative=layout_members.getChildAt(i);
			LinearLayout v = (LinearLayout) relative
					.findViewById(R.id.layout);
		if(view.getTag().equals(v.getTag()))
		{
			v.setBackgroundResource(R.drawable.border_blue);
			TextView txt_name = (TextView) v.findViewById(R.id.txt_name);
			TextView txt_witness_option = (TextView) v
					.findViewById(R.id.txt_witness_option);
			txt_name.setTextColor(getActivity().getResources().getColor(
					R.color.darkBlue_txt));
			txt_witness_option.setTextColor(getActivity().getResources().getColor(
					R.color.white_txt));
			RadioButton radioBtn=(RadioButton)v.findViewById(R.id.radioBtn);
			radioBtn.setChecked(true);
		}else{
			v.setBackgroundResource(R.drawable.border_gray);
			TextView txt_name = (TextView) v.findViewById(R.id.txt_name);
			TextView txt_witness_option = (TextView) v
					.findViewById(R.id.txt_witness_option);
			txt_name.setTextColor(getActivity().getResources().getColor(
					R.color.black_txt));
			txt_witness_option.setTextColor(getActivity().getResources().getColor(
					R.color.black_txt));
			RadioButton radioBtn=(RadioButton)v.findViewById(R.id.radioBtn);
			radioBtn.setChecked(false);
		}
			
		
		}
		
	
	}

}
