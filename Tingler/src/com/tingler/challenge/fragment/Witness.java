package com.tingler.challenge.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.ChallengeAdapter;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.DashboardTabSetterGetter;
import com.tingler.challenge.util.ProfileMemberItems;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Witness extends Fragment {
	ListView lv_challenge;
	public static ChallengeAdapter challengeeAdapter;
	ArrayList<ProfileMemberItems> witnessMemberArrayList;
	Authentication authentication;

	public Witness() {
		// TODO Auto-generated constructor stub

		this.witnessMemberArrayList = DashboardTabSetterGetter
				.getWitnessArrayList();
	}

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

		challengeeAdapter = new ChallengeAdapter(getActivity(),
				witnessMemberArrayList);
		lv_challenge.setAdapter(challengeeAdapter);
		lv_challenge.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				/**
				 * User Type- > 1= challengee,2=witness,3=watchers,4=challenger
				 * C_Status - > 0=Pending, 1=Accepted,2=Reject
				 */
				com.tingler.challenge.util.Profile profile = new com.tingler.challenge.util.Profile(
						getActivity());
				if (witnessMemberArrayList.get(position).getUser_type() == 2) {

					if (witnessMemberArrayList.get(position).getC_status() == 0) {
						authentication = new Authentication(getActivity());
						Map<String, String> params = new HashMap<String, String>();
						params.put(APIS.Challenge_id, ""
								+ witnessMemberArrayList.get(position)
										.getChallenge_id());
						params.put(APIS.CC_user_id, profile.getId());
						authentication.requestGetChallengeDetailsAPI(params,
								new AcceptReject());
					} else if (witnessMemberArrayList.get(position)
							.getC_status() == 1) {
						authentication = new Authentication(getActivity());
						Map<String, String> params = new HashMap<String, String>();
						params.put(APIS.Challenge_id, ""
								+ witnessMemberArrayList.get(position)
										.getChallenge_id());
						params.put(APIS.CC_user_id, profile.getId());
						authentication.requestGetChallengeDetailsAPI(params,
								new ChallengeMembers());
					} else if (witnessMemberArrayList.get(position)
							.getC_status() == 2) {
						Toast.makeText(getActivity(),
								"Sorry! You have rejected this challenge",
								Toast.LENGTH_LONG).show();
					}

				}
			}
		});

		return challengeView;
	}

}
