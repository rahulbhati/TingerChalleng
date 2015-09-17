package com.tingler.challenge.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tingler.challenge.MainActivity;
import com.tingler.challenge.R;
import com.tingler.challenge.adapter.OptionAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements OnClickListener {

	Toolbar toolbar;
	DrawerLayout drawerLayout;
	private View viewContainer;
	ActionBarDrawerToggle actionBarDrawerToggle;

	OptionAdapter itemAdapter;
	TextView txt_profile, txt_dashboard, txt_notification, txt_helpcenter,
			txt_aboutus, txt_update, txt_signout;
	ImageView etxt_setting;

	public NavigationDrawerFragment() {
		// Required empty public constructor
	}

	public void setUp(DrawerLayout drawerLayout, final Toolbar toolbar, int id) {
		viewContainer = getActivity().findViewById(id);
		this.toolbar = toolbar;
		this.drawerLayout = drawerLayout;
		actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),
				this.drawerLayout, toolbar, R.string.open, R.string.close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {

			}
		};
		drawerLayout.post(new Runnable() {
			@Override
			public void run() {
				actionBarDrawerToggle.syncState();
			}
		});
		drawerLayout.setDrawerListener(actionBarDrawerToggle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragmen_drawer, container, false);
	//	init(view);
		return view;
	}

	public void init(View view) {
		txt_profile=(TextView)view.findViewById(R.id.txt_profile);
		txt_dashboard=(TextView)view.findViewById(R.id.txt_dashboard);
		txt_notification=(TextView)view.findViewById(R.id.txt_notification);
		txt_helpcenter=(TextView)view.findViewById(R.id.txt_helpcenter);
		txt_aboutus=(TextView)view.findViewById(R.id.txt_aboutus);
		txt_update=(TextView)view.findViewById(R.id.txt_update);
		txt_signout=(TextView)view.findViewById(R.id.txt_signout);
		etxt_setting=(ImageView)view.findViewById(R.id.etxt_setting);
		
		txt_profile.setOnClickListener(this);
		txt_dashboard.setOnClickListener(this);
		txt_notification.setOnClickListener(this);
		txt_helpcenter.setOnClickListener(this);
		txt_aboutus.setOnClickListener(this);
		txt_update.setOnClickListener(this);
		txt_signout.setOnClickListener(this);
		etxt_setting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	//	Fragment fragment = null;
		MainActivity activity=new MainActivity();
		if(v.getId()==R.id.txt_profile){
			
		
			activity.displayView(0);
			 
	//		 fragment = new Profile();
		}else if(v.getId()==R.id.txt_dashboard){
			activity.displayView(1);
	//		 fragment = new Dashboard(getActivity());
		}else if(v.getId()==R.id.txt_notification){
			activity.displayView(2);
	//		 fragment = new Notification();
		}else if(v.getId()==R.id.txt_helpcenter){
			activity.displayView(3);
	//		 fragment = new Help();
		}else if(v.getId()==R.id.txt_aboutus){
			activity.displayView(4);
	//		 fragment = new About();
		}else if(v.getId()==R.id.txt_update){
		//	 fragment = new Upd
		}else if(v.getId()==R.id.txt_signout){
			// fragment = new HomeFragment();
		}else if(v.getId()==R.id.etxt_setting){
			// fragment = new HomeFragment();
		}
	//	 if (fragment != null) {
	//            FragmentManager fragmentManager = getFragmentManager();
	 //           fragmentManager.beginTransaction()
	//                    .replace(R.id.frame_container, fragment).commit();
	 
	            // update selected item and title, then close the drawer
	          //  mDrawerList.setItemChecked(position, true);
	          //  mDrawerList.setSelection(position);
	         //   setTitle(navMenuTitles[position]);
	         //   mDrawerLayout.closeDrawer(mDrawerList);
	  //      } else {
	            // error in creating fragment
	  //          Log.e("MainActivity", "Error in creating fragment");
	  //      }
	}
}
