package com.tingler.challenge.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.tingler.challenge.MainActivity;
import com.tingler.challenge.R;
import com.tingler.challenge.adapter.ViewPagerAdapter;
import com.tingler.challenge.api.call.Authentication;
import com.tingler.challenge.fragment.createchallenge.Details;
import com.tingler.challenge.util.SlidingTabLayout;

public class Dashboard extends Fragment implements OnClickListener {
	ViewPager viewPager;
	Authentication authentication;
	Button btn_chellenge;
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
		btn_chellenge = (Button) dashboardView.findViewById(R.id.btn_chellenge);
		btn_chellenge.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_chellenge) {
			MainActivity.toolbar_title.setText("Create Challenge");

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new Details()).commit();
		}
	}

}
