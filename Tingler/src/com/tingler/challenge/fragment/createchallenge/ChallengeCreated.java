package com.tingler.challenge.fragment.createchallenge;

import com.tingler.challenge.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ChallengeCreated extends Fragment {
	Button btn_submit;
	EditText etxt_price_type,etxt_coins;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View priceView=inflater.inflate(R.layout.fragment__challengecreated,container,false);
	//	 init(priceView);
		return priceView;
	}
	public void init(View view)
	{
		
		btn_submit=(Button)view.findViewById(R.id.btn_submit);
		etxt_price_type=(EditText)view.findViewById(R.id.etxt_price_type);
		etxt_coins=(EditText)view.findViewById(R.id.etxt_coins);
	
	}

}
