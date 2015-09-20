package com.tingler.challenge.fragment.createchallenge;

import com.tingler.challenge.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Price extends Fragment implements OnClickListener{
	Button btn_submit;
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
		
		btn_submit=(Button)view.findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
