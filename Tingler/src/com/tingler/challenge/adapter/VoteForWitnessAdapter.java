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

public class VoteForWitnessAdapter extends BaseAdapter{
	
	Context context;
    ArrayList<WitnessForVote> witnessForVoteArrayList = null;
    RadioGroup radioGroup;
    ArrayList<RadioButton> radioButtonsArrayList;
	public VoteForWitnessAdapter(Context context, ArrayList<WitnessForVote> witness) {
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
		final View view = mInflater.inflate(R.layout.row_votforwitness, null);
		TextView txt_name=(TextView)view.findViewById(R.id.txt_name);
		 final RadioButton radioBtn=(RadioButton)view.findViewById(R.id.radioBtn);
		 radioBtn.setId(position);
		 
		 txt_name.setText(witnessForVoteArrayList.get(position).getName());
		
		radioButtonsArrayList.add(radioBtn);
		 radioBtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				
				
				for (int i = 0; i < 3; i++) {
					
					
		 //     ((Checkable)(RelativeLayout)VoteForWitness.lv_witness.getChildAt(i)).getChildAt(0));
					
					try {
						 ((Checkable) (RelativeLayout)VoteForWitness.lv_witness.getChildAt(i)).setChecked(false);
								
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e);
					}
					
				}
				
				radioBtn.setChecked(isChecked);
			}
		});
		 
		 
		return view;
		
	}

}
