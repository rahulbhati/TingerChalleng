package com.tingler.challenge.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.ChallengeMembersAdapter;
import com.tingler.challenge.util.MembersItems;

public class ChallengeMembers extends Fragment{
ListView lv_members;
ArrayList<MembersItems> memberArrayList;
ChallengeMembersAdapter membersAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View mermbersView=inflater.inflate(R.layout.fragment_challenge_members,container,false);
		init(mermbersView);
		return init(mermbersView);
	}
public View init(View view){
	lv_members=(ListView)view.findViewById(R.id.lv_members);
	memberArrayList=new ArrayList<MembersItems>();
	
	for (int i = 0; i < 5; i++) {
		MembersItems items=new MembersItems();
		items.setName("Item "+i);
		memberArrayList.add(items);
		
	}
	membersAdapter=new ChallengeMembersAdapter(getActivity(), memberArrayList);
	lv_members.setAdapter(membersAdapter);
	
	return view;
}
}
