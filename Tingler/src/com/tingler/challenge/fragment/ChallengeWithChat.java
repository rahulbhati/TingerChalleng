package com.tingler.challenge.fragment;

import com.tingler.challenge.R;
import com.tingler.challenge.util.GetChallengeDetailsItems;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChallengeWithChat extends Fragment implements OnClickListener{
TextView txt_challengename;
EditText etxt_days,etxt_hours,etxt_minutes;
ImageView imv_uparrow;
GetChallengeDetailsItems getChallengeDetailsItems;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View challengeWithChatView=inflater.inflate(R.layout.fragment_challenge_with_chat,container,false);
		init(challengeWithChatView);
		return challengeWithChatView;
	}
public void init(View view){
	txt_challengename=(TextView)view.findViewById(R.id.txt_challengename);
	etxt_days=(EditText)view.findViewById(R.id.etxt_days);
	etxt_hours=(EditText)view.findViewById(R.id.etxt_hours);
	etxt_minutes=(EditText)view.findViewById(R.id.etxt_minutes);
	imv_uparrow=(ImageView)view.findViewById(R.id.imv_uparrow);
	imv_uparrow.setOnClickListener(this);
	etxt_days.setEnabled(false);
	etxt_hours.setEnabled(false);
	etxt_minutes.setEnabled(false);
	
	setValue();
}
public void setValue(){
	getChallengeDetailsItems=new GetChallengeDetailsItems();
	txt_challengename.setText(getChallengeDetailsItems.getTitle());
	etxt_days.setText(getChallengeDetailsItems.getC_day());
	etxt_hours.setText(getChallengeDetailsItems.getC_hrs());
	etxt_minutes.setText(getChallengeDetailsItems.getC_min());
}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	if(v.getId()==R.id.imv_uparrow){
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.frame_container, new ChallengeMembers()).commit();
	
	}
}

}
