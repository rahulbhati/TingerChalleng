package com.tingler.challenge.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.VoteForWitnessAdapter;
import com.tingler.challenge.util.WitnessForVote;

public class VoteForWitness extends Fragment implements OnClickListener{
	Button btn_next;
	public static ListView lv_witness;
	VoteForWitnessAdapter votforwitnessAdapter;
	ArrayList<WitnessForVote> witnessForVoteArrayList = null;
	RadioGroup radioGroup;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View witnesssView=inflater.inflate(R.layout.fragment_voteforwitness,container,false);
		init(witnesssView);
		return witnesssView;
	}
	public void init(View view) {

		btn_next = (Button) view.findViewById(R.id.btn_next);
		lv_witness = (ListView) view.findViewById(R.id.lv_witness);
		
		btn_next.setOnClickListener(this);
		witnessForVoteArrayList=new ArrayList<WitnessForVote>();
		for (int i = 0; i < 5; i++) {
			
			WitnessForVote vote=new WitnessForVote();
			vote.setName("Item :"+i);
			witnessForVoteArrayList.add(vote);
			
		}
		
	//	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice,witnessForVoteArrayList);
	//	 lv_witness.setAdapter(adapter);
			votforwitnessAdapter = new VoteForWitnessAdapter(getActivity(), witnessForVoteArrayList);
		lv_witness.setAdapter(votforwitnessAdapter);
		
		lv_witness.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), ""+lv_witness.getCheckedItemIds(),Toast.LENGTH_LONG).show();
				
			}
		});
		
		lv_witness.setOnItemSelectedListener(new OnItemSelectedListener() {

			
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), ""+lv_witness.getCheckedItemIds(),Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
