package com.tingler.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class CreateChallengeDescriptionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent inten=new Intent(this,WelcomeActivity.class);
        startActivity(inten);
        setContentView(R.layout.activity_createchallengedescription);
    }

  
}
