package com.tingler.challenge.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tingler.challenge.R;
import com.tingler.challenge.fragment.createchallenge.Details;

public class Profile extends Fragment implements OnClickListener {
	ProgressBar progressbar_profileLevel;
	int pStatus = 0;
	private Handler handler = new Handler();
	TextView txt_edit,txt_level_number,txt_username,txt_coins;
	com.tingler.challenge.util.Profile profile;
Button btn_challenge;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View profileView = inflater.inflate(R.layout.fragment_profile,
				container, false);
		init(profileView);
		return profileView;
	}

	public void init(View view) {
		profile = new com.tingler.challenge.util.Profile(getActivity());
		progressbar_profileLevel = (ProgressBar) view
				.findViewById(R.id.progressbar_profileLevel);
		txt_edit = (TextView) view.findViewById(R.id.txt_edit);
		txt_level_number=(TextView)view.findViewById(R.id.txt_level_number);
		txt_username=(TextView)view.findViewById(R.id.txt_username);
		txt_coins=(TextView)view.findViewById(R.id.txt_coins);
		btn_challenge=(Button)view.findViewById(R.id.btn_challenge);
		btn_challenge.setOnClickListener(this);
		txt_edit.setOnClickListener(this);
		setValues();
		progressBarLevel();
	}

	public void setValues() {

		txt_username.setText(profile.getFullName());
		
	}

	public void progressBarLevel() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (pStatus <= 100) {

					handler.post(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							progressbar_profileLevel.setProgress(pStatus);
							// progressBar1.setSecondaryProgress(pStatus + 5);

						}
					});
					try {
						// Sleep for 200 milliseconds.
						// Just to display the progress slowly
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pStatus++;
				}
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.txt_edit) {

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new EditProfile()).commit();
		}else if(v.getId()==R.id.btn_challenge){
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new Details()).commit();
		}
	}
}
