package com.tingler.challenge.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.ChallengeAdapter;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.ProfileMemberItems;

public class Challenge extends Fragment {
	ListView lv_challenge;
	public static ChallengeAdapter challengeeAdapter;
	ArrayList<ProfileMemberItems> challengeMemberArrayList;
	  Authentication authentication;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View challengeView = inflater.inflate(R.layout.fragment_members,
				container, false);
		return init(challengeView);
	}

	public View init(View view) {
		View challengeView = view;
		lv_challenge = (ListView) view.findViewById(R.id.lv_challenge);
		challengeMemberArrayList = new ArrayList<ProfileMemberItems>();

		for (int i = 0; i < 5; i++) {
			ProfileMemberItems items = new ProfileMemberItems();
			items.setName("Rahul");
			items.setProgressBarLevel(20 * i);
			challengeMemberArrayList.add(items);
		}

		challengeeAdapter = new ChallengeAdapter(getActivity(),
				challengeMemberArrayList);
		lv_challenge.setAdapter(challengeeAdapter);
		
		lv_challenge.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			//	Toast.makeText(getActivity(), ""+view.getTag(), Toast.LENGTH_LONG).show();
				
				authentication=new Authentication(getActivity());
				Map<String, String> params = new HashMap<String, String>();
				params.put(APIS.Challenge_id, "5");
				authentication.requestGetChallengeDetailsAPI(params);
				
			}
		});
		
		
		return challengeView;
	}
}
