package com.tingler.challenge.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tingler.challenge.R;
import com.tingler.challenge.adapter.ViewPagerAdapter;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.util.SlidingTabLayout;

public class Dashboard extends Fragment {
	ViewPager viewPager;
	Authentication authentication;
	public static ViewPagerAdapter viewPagerAdapter;
	SlidingTabLayout tabs;
	CharSequence Titles[] = { "Challenge", "Witness", "Watchers" };
	int Numboftabs = 3;
	// Context context;
	private FragmentActivity fragmentActivity;

	@Override
	public void onAttach(Activity activity) {
		fragmentActivity = (FragmentActivity) activity;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View dashboardView = inflater.inflate(R.layout.fragment_dashboard,
				container, false);
		init(dashboardView);
		return dashboardView;
	}

	public void init(View dashboardView) {
		viewPager = (ViewPager) dashboardView.findViewById(R.id.viewPager);
		viewPagerAdapter = new ViewPagerAdapter(
				fragmentActivity.getSupportFragmentManager(), Titles,
				Numboftabs);
		viewPager.setAdapter(viewPagerAdapter);
		tabs = (SlidingTabLayout) dashboardView.findViewById(R.id.slidingTabs);
		tabs.setDistributeEvenly(true);
		tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return getResources().getColor(R.color.bottle_green_color);
			}
		});
		// Setting the ViewPager For the SlidingTabsLayout
		tabs.setViewPager(viewPager);

	}

}
