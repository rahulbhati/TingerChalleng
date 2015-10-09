package com.tingler.challenge.fragment.createchallenge;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.tingler.challenge.R;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.fragment.VoteForWitness;
import com.tingler.challenge.util.Profile;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Price extends Fragment implements OnClickListener{
	Button btn_submit,btn_back;
	EditText etxt_price_type,etxt_coins;
	Profile profile;	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View priceView=inflater.inflate(R.layout.fragment__createchallenge_price,container,false);
		 init(priceView);
		return priceView;
	}
	public void init(View view)
	{
		btn_back = (Button) view.findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);
		btn_submit=(Button)view.findViewById(R.id.btn_submit);
		etxt_price_type=(EditText)view.findViewById(R.id.etxt_price_type);
		etxt_coins=(EditText)view.findViewById(R.id.etxt_coins);
		btn_submit.setOnClickListener(this);
		profile=new Profile(getActivity());	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId()==R.id.btn_submit){
		/*	FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new ChallengeCreated()).commit();
	*/		/*FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new VoteForWitness()).commit();*/
			try {
				int coin=Integer.parseInt(etxt_coins.getText().toString());
				if(coin>=200 && coin<=Integer.parseInt(profile.getCoins())){
					submitChallenge();
				}else{
					 Toast.makeText(getActivity(), "Please enter minimum 200 coin or less then your current coin", Toast.LENGTH_LONG).show();
				}
					
			} catch (Exception e) {
				// TODO: handle exception
			   Toast.makeText(getActivity(), "Please enter valid Coin", Toast.LENGTH_LONG).show();
			}
			
			
		
		}else if(v.getId()==R.id.btn_back){
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new Witness()).commit();
		}

		
	}
	public void submitChallenge(){

		

		String prize=etxt_price_type.getText().toString();
	//	int coin=Integer.parseInt(etxt_coins.getText().toString());
		String coin=etxt_coins.getText().toString();
		String title=SetterGetter.getTitle();
		String description=SetterGetter.getDescription();
		String days=SetterGetter.getDays();
		String hrs=SetterGetter.getHours();
		String mins=SetterGetter.getMinutes();
		
	
		String user_id=profile.getId();
		
		String challengee=null;
		
		for (int i = 0; i < SetterGetter.getChallengeeTempArrayList().size(); i++) {
		   if(challengee!=null){
			challengee=challengee+","+SetterGetter.getChallengeeTempArrayList().get(i).getMobile();
		   }else{
			   challengee=SetterGetter.getChallengeeTempArrayList().get(i).getMobile();
		   }
			
			
		}
		String witness=null;
		for (int i = 0; i < SetterGetter.getWitnessTempArrayList().size(); i++) {
			
			 if(witness!=null){
				 witness=witness+","+SetterGetter.getWitnessTempArrayList().get(i).getMobile();
				   }else{
					   witness=SetterGetter.getWitnessTempArrayList().get(i).getMobile();
				   }
		
		}
		
		HashMap<String, String> createChallengeHashMap = new HashMap<String, String>();
		createChallengeHashMap.put(APIS.CC_title, title);
		createChallengeHashMap.put(APIS.CC_description, description);
		createChallengeHashMap.put(APIS.CC_days, days);
		createChallengeHashMap.put(APIS.CC_hrs, hrs);
		createChallengeHashMap.put(APIS.CC_mins, mins);
		createChallengeHashMap.put(APIS.CC_challengee,challengee);
		createChallengeHashMap.put(APIS.CC_witness, witness);
		createChallengeHashMap.put(APIS.CC_coin, coin);
		createChallengeHashMap.put(APIS.CC_user_id, user_id);
		
		System.out.println("input :"+createChallengeHashMap);
		Authentication authentication=new Authentication(getActivity());
		authentication.requestCreateChallengeAPI(createChallengeHashMap);
		
		}
		

}
