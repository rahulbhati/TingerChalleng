package com.tingler.challenge.fragment.createchallenge;

import com.tingler.challenge.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Challengee extends Fragment implements OnClickListener{
	Button btn_next;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View challengeeView=inflater.inflate(R.layout.fragment__createchallenge_challengee,container,false);
		init(challengeeView);
		return challengeeView;
	}
	public void init(View view)
	{
		
		btn_next=(Button)view.findViewById(R.id.btn_next);
		btn_next.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_next){
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new Witness()).commit();
		}
	}
}
