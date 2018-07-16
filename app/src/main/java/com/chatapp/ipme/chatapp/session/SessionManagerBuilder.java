package com.chatapp.ipme.chatapp.session;

import android.app.Application;

import com.chatapp.ipme.chatapp.session.SessionKeys;
import com.chatapp.ipme.chatapp.session.SessionManager;


public class SessionManagerBuilder extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new SessionManager.Builder()
                .setContext(getApplicationContext())
                .setPrefsName(SessionKeys.PREFS_NAME.getKey())
                .build();
    }
}

