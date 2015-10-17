package com.tingler.challenge.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tingler.challenge.R;
import com.tingler.challenge.fragment.VoteForWitness;
import com.tingler.challenge.util.WitnessForVote;

public class SelectWinnerAdapter extends BaseAdapter{
	
	Context context;
    ArrayList<WitnessForVote> witnessForVoteArrayList = null;
    RadioGroup radioGroup;
    ArrayList<RadioButton> radioButtonsArrayList;
	public SelectWinnerAdapter(Context context, ArrayList<WitnessForVote> witness) {
		this.context = context;
		this.witnessForVoteArrayList = witness;
		radioButtonsArrayList=new ArrayList<RadioButton>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return witnessForVoteArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return witnessForVoteArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		final View view = mInflater.inflate(R.layout.row_witness_vote, null);

		return view;
		
	}

}
