package com.tingler.challenge.fragment;

import com.tingler.challenge.R;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class Profile extends Fragment {
	ProgressBar progressbar_profileLevel;
	int pStatus = 0;
	private Handler handler = new Handler();

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
		progressbar_profileLevel = (ProgressBar) view
				.findViewById(R.id.progressbar_profileLevel);
		progressBarLevel();
	}
	
	public void progressBarLevel(){
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
							//progressBar1.setSecondaryProgress(pStatus + 5);
							
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
}
