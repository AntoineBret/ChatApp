package com.chatapp.ipme.chatapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.chatapp.ipme.chatapp.session.SessionKeys;
import com.chatapp.ipme.chatapp.session.SessionManager;
import com.chatapp.ipme.chatapp.ui.firstConnection.FirstConnectionFragment;
import com.chatapp.ipme.chatapp.ui.login.LogInFragment;

public class ConnectToServiceActivity extends AppCompatActivity {

  private FrameLayout frameLayout;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecttoservice);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        frameLayout = findViewById(R.id.login_frame_container);

        if (!sharedPreferences.getBoolean(FirstConnectionFragment.COMPLETED_TERMS_OF_SERVICE, false)) {
            // The user hasn't seen the FirstConnectionFragment yet, so show it
            Fragment f = FirstConnectionFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_frame_container, f)
                    .commit();
        } else if (SessionManager.getString(SessionKeys.KEY_TOKEN.getKey(), null) == null) {
//             Code to run if token return null
            Fragment f = LogInFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_frame_container, f)
                    .commit();
        } else if (intent == null) {
            intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}
