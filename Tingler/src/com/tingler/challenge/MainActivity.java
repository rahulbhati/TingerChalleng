package com.tingler.challenge;

import com.tingler.challenge.fragment.NavigationDrawerFragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends ActionBarActivity {
	Toolbar toolbar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        NavigationDrawerFragment fragmentDrawer =(NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.drawerFragment);
        fragmentDrawer.setUp(drawerLayout,toolbar,R.id.drawerFragment);
    }

  
}
