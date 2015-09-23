package com.tingler.challenge;

import com.tingler.challenge.fragment.About;
import com.tingler.challenge.fragment.Dashboard;
import com.tingler.challenge.fragment.Help;
import com.tingler.challenge.fragment.NavigationDrawerFragment;
import com.tingler.challenge.fragment.Notification;
import com.tingler.challenge.fragment.Profile;
import com.tingler.challenge.fragment.createchallenge.Challengee;
import com.tingler.challenge.fragment.createchallenge.Details;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	Toolbar toolbar;
	DrawerLayout drawerLayout;
	ImageView imv_action_menu;
	TextView txt_profile, txt_dashboard, txt_notification, txt_helpcenter,
			txt_aboutus, txt_update, txt_signout,toolbar_title;
	ImageView etxt_setting;
    Button btn_createchallenge;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
	//	setContentView(R.layout.welcome_test);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		init();
	}

	public void init() {
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		imv_action_menu = (ImageView) findViewById(R.id.imv_action_menu);

		txt_profile = (TextView) findViewById(R.id.txt_profile);
		txt_dashboard = (TextView) findViewById(R.id.txt_dashboard);
		txt_notification = (TextView) findViewById(R.id.txt_notification);
		txt_helpcenter = (TextView) findViewById(R.id.txt_helpcenter);
		txt_aboutus = (TextView) findViewById(R.id.txt_aboutus);
		txt_update = (TextView) findViewById(R.id.txt_update);
		txt_signout = (TextView) findViewById(R.id.txt_signout);
		toolbar_title = (TextView) findViewById(R.id.toolbar_title);
		etxt_setting = (ImageView) findViewById(R.id.etxt_setting);
		btn_createchallenge=(Button)findViewById(R.id.btn_createchallenge);
		imv_action_menu.setOnClickListener(this);
		txt_profile.setOnClickListener(this);
		txt_dashboard.setOnClickListener(this);
		txt_notification.setOnClickListener(this);
		txt_helpcenter.setOnClickListener(this);
		txt_aboutus.setOnClickListener(this);
		txt_update.setOnClickListener(this);
		txt_signout.setOnClickListener(this);
		etxt_setting.setOnClickListener(this);
		btn_createchallenge.setOnClickListener(this);
		displayView(0);
		toolbar_title.setText("Profile");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.imv_action_menu) {
			drawerLayout.openDrawer(Gravity.END);
		} else if (v.getId() == R.id.txt_profile) {
			displayView(0);
			toolbar_title.setText("Profile");
		} else if (v.getId() == R.id.txt_dashboard) {
			displayView(1);
			toolbar_title.setText("Dashboard");
		}else if (v.getId() == R.id.txt_notification) {
			displayView(2);
			toolbar_title.setText("Notification");
		}else if (v.getId() == R.id.txt_helpcenter) {
			displayView(3);
			toolbar_title.setText("Help Center");
		}else if (v.getId() == R.id.txt_aboutus) {
			displayView(4);
			toolbar_title.setText("About Us");
		}else if (v.getId() == R.id.txt_aboutus) {
			displayView(4);
			toolbar_title.setText("About Us");
		}else if(v.getId()==R.id.btn_createchallenge){
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new Details()).commit();
			drawerLayout.closeDrawers();
		}

	}

	public void displayView(int position) {
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new Profile();
			break;
		case 1:
			fragment = new Dashboard();
			break;
		case 2:
			fragment = new Notification();
			break;
		case 3:
			fragment = new Help();
			break;
		case 4:
			fragment = new About();
			break;
		case 5:
			fragment = new Details();
			break;
       
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			drawerLayout.closeDrawers();
		} else {

			Log.e("MainActivity", "Error in creating fragment");
		}
	}

}
