package com.tingler.challenge.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tingler.challenge.fragment.Challenge;
import com.tingler.challenge.fragment.Watchers;
import com.tingler.challenge.fragment.Witness;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
	CharSequence Titles[];
	int NumbOfTabs;
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[],
			int mNumbOfTabsumb) {
		super(fm);

		this.Titles = mTitles;
		this.NumbOfTabs = mNumbOfTabsumb;
	}
   @Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		if (position == 0) {
			fragment = new Challenge();

		} else if (position == 1) {
			fragment = new Witness();

		} else if (position == 2) {
			fragment = new Watchers();
		}
		return fragment;
	}
    @Override
	public CharSequence getPageTitle(int position) {
		return Titles[position];
	}
    @Override
	public int getCount() {
		return NumbOfTabs;
	}
}