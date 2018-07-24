package com.chatapp.ipme.chatapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.chatapp.ipme.chatapp.session.SessionKeys;
import com.chatapp.ipme.chatapp.session.SessionManager;
import com.chatapp.ipme.chatapp.ui.settings.SettingsFragment;

public class ParameterActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter);

        frameLayout = findViewById(R.id.parameter_frame_container);

        if (savedInstanceState == null) {
            Fragment f = SettingsFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.parameter_frame_container, f)
                    .disallowAddToBackStack()
                    .commit();
        }
    }
}
