package com.tingler.challenge.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.TabPagerAdapter;

public class Dashboard extends Fragment{
	ViewPager viewPager;
    TabPagerAdapter tabAdapter;
    Context context;
    
    public Dashboard(){
    	
    }
    public Dashboard(Context context){
    	this.context=context;
    }
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View dashboardView=inflater.inflate(R.layout.fragment_dashboard,container,false);
		//init(dashboardView);
		
		return dashboardView;
	}
public void init(View dashboardView){
	viewPager = (ViewPager)dashboardView.findViewById(R.id.viewPager);
	tabAdapter = new TabPagerAdapter(((FragmentActivity)this.context).getSupportFragmentManager());
	viewPager.setAdapter(tabAdapter);
}
}
