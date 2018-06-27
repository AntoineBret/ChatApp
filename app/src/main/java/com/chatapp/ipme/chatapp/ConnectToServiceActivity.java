package com.chatapp.ipme.chatapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.chatapp.ipme.chatapp.ui.login.LogInFragment;

public class ConnectToServiceActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecttoservice);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        frameLayout = findViewById(R.id.login_frame_container);

        firstConnectionFrame();

    }

    //First connection Accept Terms and Conditions
    private void firstConnectionFrame() {
        LayoutInflater.from(getApplicationContext()).inflate(R.layout.welcome_cardview, frameLayout, true);
        Button buttonTermServiceAgree = findViewById(R.id.buttonTermServiceAgree);
        buttonTermServiceAgree.setOnClickListener(view -> {
            Fragment f = LogInFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.login_frame_container, f).addToBackStack(null).commit();
        });
    }
}
