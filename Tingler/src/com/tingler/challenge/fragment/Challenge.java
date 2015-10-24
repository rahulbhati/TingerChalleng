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

import com.tingler.challenge.MainActivity;
import com.tingler.challenge.R;
import com.tingler.challenge.adapter.ChallengeAdapter;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.DashboardTabSetterGetter;
import com.tingler.challenge.util.ProfileMemberItems;
import com.tingler.challenge.util.SetterGetter;

public class Challenge extends Fragment {
	ListView lv_challenge;
	public static ChallengeAdapter challengeeAdapter;
	ArrayList<ProfileMemberItems> challengeMemberArrayList;
	Authentication authentication;
	public Challenge() {
		// TODO Auto-generated constructor stub
		this.challengeMemberArrayList = DashboardTabSetterGetter
				.getChallengeArrayList();
	}
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
				challengeMemberArrayList);
		lv_challenge.setAdapter(challengeeAdapter);

		lv_challenge.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				 
				com.tingler.challenge.util.Profile profile = new com.tingler.challenge.util.Profile(
						getActivity());
				/**
				 * User Type- > 1= challengee,2=witness,3=watchers,4=challenger
				 * C_Status - > 0=Pending, 1=Accepted,2=Reject
				 */

				if (challengeMemberArrayList.get(position).getUser_type() == 1) {
					 SetterGetter.setUserType(1);
					if (challengeMemberArrayList.get(position).getC_status() == 0) {
						authentication = new Authentication(getActivity());
						Map<String, String> params = new HashMap<String, String>();
						params.put(APIS.Challenge_id, ""
								+ challengeMemberArrayList.get(position)
										.getChallenge_id());
						params.put(APIS.CC_user_id, profile.getId());
						authentication.requestGetChallengeDetailsAPI(params,
								new AcceptReject());
					}else if (challengeMemberArrayList.get(position)
							.getC_status() == 1) {
						authentication = new Authentication(getActivity());
						Map<String, String> params = new HashMap<String, String>();
						params.put(APIS.Challenge_id, ""
								+ challengeMemberArrayList.get(position)
										.getChallenge_id());
						params.put(APIS.CC_user_id, profile.getId());
						authentication.requestGetChallengeDetailsAPI(params,
								new ChallengeMembers());
					}
					else if(challengeMemberArrayList.get(position)
							.getC_status() == 2){
						  Toast.makeText(getActivity(),"Sorry! You have rejected this challenge",Toast.LENGTH_LONG).show();
					}

				} else if (challengeMemberArrayList.get(position)
						.getUser_type() == 4) {
					 SetterGetter.setUserType(4);
					authentication = new Authentication(getActivity());
					Map<String, String> params = new HashMap<String, String>();
					params.put(APIS.Challenge_id, ""
							+ challengeMemberArrayList.get(position)
									.getChallenge_id());
					params.put(APIS.CC_user_id, profile.getId());
					authentication.requestGetChallengeDetailsAPI(params,
							new ChallengePendding());
				}
			}
		});

		return challengeView;
	}
   
}
