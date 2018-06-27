package com.chatapp.ipme.chatapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.chatapp.ipme.chatapp.ui.login.LogInFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            Fragment f = LogInFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
        }
    }
}

