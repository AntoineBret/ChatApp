package com.chatapp.ipme.chatapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.chatapp.ipme.chatapp.ui.contact.ContactFragment;

public class ContactActivity extends AppCompatActivity {

  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact);

    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    if (savedInstanceState == null) {
      Fragment f = ContactFragment.newInstance();
      getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.contact_frame_container, f)
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
