package com.tingler.challenge.fragment;

import com.tingler.challenge.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Help extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View helpView=inflater.inflate(R.layout.fragment_help,container,false);
		return helpView;
	}

}
