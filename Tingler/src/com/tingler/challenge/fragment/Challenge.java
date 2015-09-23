package com.tingler.challenge.fragment;

import com.tingler.challenge.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Challenge extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View witnessView = inflater.inflate(R.layout.fragment_challenge,
				container, false);
		return witnessView;
	}
}
