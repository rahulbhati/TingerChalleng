package com.tingler.challenge.fragment.createchallenge;

import com.tingler.challenge.R;
import com.tingler.challenge.fragment.EditProfile;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Details extends Fragment implements OnClickListener{
EditText etxt_title,etxt_description;
Button btn_next;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View createChalleneView=inflater.inflate(R.layout.fragment__createchallenge_details,container,false);
		init(createChalleneView);
		return createChalleneView;
	}
public void init(View view)
{
	etxt_title=(EditText)view.findViewById(R.id.etxt_title);
	etxt_description=(EditText)view.findViewById(R.id.etxt_description);
	btn_next=(Button)view.findViewById(R.id.btn_next);
	btn_next.setOnClickListener(this);
}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	if(v.getId()==R.id.btn_next){
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, new Challengee()).commit();
	}
}
}
