package com.tingler.challenge.fragment;

import java.util.ArrayList;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.ChallengeAdapter;
import com.tingler.challenge.util.ProfileMemberItems;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class Watchers extends Fragment {
	ListView lv_challenge;
	public static ChallengeAdapter challengeeAdapter;
	ArrayList<ProfileMemberItems> challengeMemberArrayList;

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
		return challengeView;
	}
}
