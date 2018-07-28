package com.chatapp.ipme.chatapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.chatapp.ipme.chatapp.ui.settings.SettingsFragment;

public class ParameterActivity extends AppCompatActivity {

  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_parameter);

    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    if (savedInstanceState == null) {
      Fragment f = SettingsFragment.newInstance();
      getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.parameter_frame_container, f)
        .disallowAddToBackStack()
        .commit();
    }
  }

  @Override
  public boolean onSupportNavigateUp() {
    //Enable on back pressed for toolbar back arrow
    onBackPressed();
    return true;
  }
}
