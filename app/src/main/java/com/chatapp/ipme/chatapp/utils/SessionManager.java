package com.chatapp.ipme.chatapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.chatapp.ipme.chatapp.ConnectToServiceActivity;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private Context _context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int PRIVATE_MODE = 0;
    private static String PREF_NAME = "ChatAppPref";
    private static String IS_LOGIN = "IsLoggedIn";
    public static String KEY_NAME = "Account";
    public static String KEY_PASSWORD = "Password";
    public static String KEY_TOKEN = null;

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
        editor.apply();
        editor.commit();
    }

    public Map<String, String> getUserDetails() {
        Map<String, String> user = new HashMap<>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));

        return user;
    }

    public void logoutUser() {
        editor.putBoolean(IS_LOGIN, false);
        editor.remove(KEY_NAME);
        editor.remove(KEY_PASSWORD);
        editor.remove(KEY_TOKEN);
        editor.apply();
        editor.commit();
        Intent intent = new Intent(_context, ConnectToServiceActivity.class);
        _context.startActivity(intent);

    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
