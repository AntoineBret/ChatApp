package com.chatapp.ipme.chatapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.chatapp.ipme.chatapp.session.SessionKeys;
import com.chatapp.ipme.chatapp.session.SessionManager;
import com.chatapp.ipme.chatapp.ui.contact.ContactFragment;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        if(savedInstanceState == null){
            Fragment f = ContactFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contact_frame_container, f)
                    .commit();
        }
    }
}
