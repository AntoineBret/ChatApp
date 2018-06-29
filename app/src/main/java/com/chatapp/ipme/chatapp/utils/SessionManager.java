package com.chatapp.ipme.chatapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.chatapp.ipme.chatapp.HomeActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.ui.login.LogInFragment;

import java.util.HashMap;

public class SessionManager {

    private Context _context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "ChatAppPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "Account";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_TOKEN = null;

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String password, String token) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Fragment f = LogInFragment.newInstance();
            ((HomeActivity) _context)
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, f)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));

        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        Fragment f = LogInFragment.newInstance();
        ((HomeActivity) _context)
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, f)
                .addToBackStack(null)
                .commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
