package com.tingler.challenge;

import com.tingler.challenge.fragment.About;
import com.tingler.challenge.fragment.Dashboard;
import com.tingler.challenge.fragment.Help;
import com.tingler.challenge.fragment.NavigationDrawerFragment;
import com.tingler.challenge.fragment.Notification;
import com.tingler.challenge.fragment.Profile;

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
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	Toolbar toolbar;
    DrawerLayout drawerLayout;
    ImageView imv_action_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
         init();
       //  fragmentDrawer.setUp(drawerLayout,toolbar,R.id.drawerFragment);
        
    }
public void init(){
	  drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
      NavigationDrawerFragment fragmentDrawer =(NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.drawerFragment);
      imv_action_menu=(ImageView)findViewById(R.id.imv_action_menu);
     
      imv_action_menu.setOnClickListener(this);
      
}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	 drawerLayout.openDrawer(Gravity.END);
	 
}
public void displayView(int position){
	Fragment fragment = null;
    switch (position) {
    case 0:
        fragment = new Profile();
        break;
    case 1:
        fragment = new Dashboard(this);
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
  
    default:
        break;
    }

	 if (fragment != null) {
           FragmentManager fragmentManager = getFragmentManager();
           fragmentManager.beginTransaction()
                   .replace(R.id.frame_container, fragment).commit();

           // update selected item and title, then close the drawer
         //  mDrawerList.setItemChecked(position, true);
         //  mDrawerList.setSelection(position);
        //   setTitle(navMenuTitles[position]);
           drawerLayout.closeDrawers();
       } else {
           // error in creating fragment
           Log.e("MainActivity", "Error in creating fragment");
       }
}
  
}
