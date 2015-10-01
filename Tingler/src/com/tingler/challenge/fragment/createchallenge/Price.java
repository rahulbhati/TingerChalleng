package com.tingler.challenge.fragment.createchallenge;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.tingler.challenge.R;
import com.tingler.challenge.api.call.APIS;
import com.tingler.challenge.api.call.Authentication;
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

public class Price extends Fragment implements OnClickListener{
	Button btn_submit,btn_back;
	EditText etxt_price_type,etxt_coins;
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
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId()==R.id.btn_submit){
		
		String title=SetterGetter.getTitle();
		String description=SetterGetter.getDescription();
		String days=SetterGetter.getDays();
		String hrs=SetterGetter.getHours();
		String mins=SetterGetter.getMinutes();
		
		String prize=etxt_price_type.getText().toString();
		String coin=etxt_coins.getText().toString();
		
		Profile profile=new Profile(getActivity());
		String user_id=profile.getId();
		
		JSONObject challengeeJsonObject=new JSONObject();
		
		
		for (int i = 0; i < SetterGetter.getChallengeeTempArrayList().size(); i++) {
			JSONObject tempObj=new JSONObject();
			
			try {
				tempObj.put("name", SetterGetter.getChallengeeTempArrayList().get(i).getName());
				tempObj.put("mobile", SetterGetter.getChallengeeTempArrayList().get(i).getMobile());
				
				challengeeJsonObject.put(""+i,tempObj);	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JSONObject witnessJsonObject=new JSONObject();
		for (int i = 0; i < SetterGetter.getWitnessTempArrayList().size(); i++) {
			JSONObject tempObj=new JSONObject();
			
			try {
				tempObj.put("name", SetterGetter.getWitnessTempArrayList().get(i).getName());
				tempObj.put("mobile", SetterGetter.getWitnessTempArrayList().get(i).getMobile());
				
				witnessJsonObject.put(""+i,tempObj);	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String challengee=challengeeJsonObject.toString();
		String witness=witnessJsonObject.toString();
		
		HashMap<String, String> createChallengeHashMap = new HashMap<String, String>();
		createChallengeHashMap.put(APIS.CC_title, title);
		createChallengeHashMap.put(APIS.CC_description, description);
		createChallengeHashMap.put(APIS.CC_days, days);
		createChallengeHashMap.put(APIS.CC_hrs, hrs);
		createChallengeHashMap.put(APIS.CC_mins, mins);
		createChallengeHashMap.put(APIS.CC_challengee, challengee);
		createChallengeHashMap.put(APIS.CC_witness, witness);
		createChallengeHashMap.put(APIS.CC_coin, coin);
		createChallengeHashMap.put(APIS.CC_user_id, user_id);
		
		Authentication authentication=new Authentication(getActivity());
		authentication.requestCreateChallengeAPI(createChallengeHashMap);
		
		}else if(v.getId()==R.id.btn_back){
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new Witness()).commit();
		}
		
		
		
	}
}
