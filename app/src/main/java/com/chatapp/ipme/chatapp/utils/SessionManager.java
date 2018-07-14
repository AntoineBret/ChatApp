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
  private static String IS_LOGIN;
  public static String KEY_TOKEN = null;
  public static int KEY_ID;
  public static String KEY_USERNAME;
  public static String KEY_PASSWORD;
  public static String KEY_FIRSTNAME;
  public static String KEY_LASTNAME;
  public static String KEY_EMAIL;

  public SessionManager(Context context) {
    this._context = context;
    pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    editor = pref.edit();
  }

  public void createLoginSession(String token, int id, String name, String password, String firstname, String lastname, String email) {
    editor.putBoolean(IS_LOGIN, true);
    editor.putString(KEY_TOKEN, token);
    editor.putInt(KEY_ID, id);
    editor.putString(KEY_USERNAME, name);
    editor.putString(KEY_PASSWORD, password);
    editor.putString(KEY_FIRSTNAME, firstname);
    editor.putString(KEY_LASTNAME, lastname);
    editor.putString(KEY_EMAIL, email);
    editor.apply();
    editor.commit();
  }

  public Map<String, String> getUserDetails() {
    Map<String, String> user = new HashMap<>();
    user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));
    user.put(KEY_ID, pref.getInt((KEY_ID), 0));
    user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
    user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
    user.put(KEY_FIRSTNAME, pref.getString(KEY_FIRSTNAME, null));
    user.put(KEY_LASTNAME, pref.getString(KEY_LASTNAME, null));
    user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

    return user;
  }

  public void logoutUser() {
    editor.putBoolean(IS_LOGIN, false);
    editor.remove(KEY_TOKEN);
    editor.remove(KEY_ID);
    editor.remove(KEY_USERNAME);
    editor.remove(KEY_PASSWORD);
    editor.remove(KEY_FIRSTNAME);
    editor.remove(KEY_LASTNAME);
    editor.remove(KEY_EMAIL);
    editor.apply();
    editor.commit();
    Intent intent = new Intent(_context, ConnectToServiceActivity.class);
    _context.startActivity(intent);

  }

  public boolean isLoggedIn() {
    return pref.getBoolean(IS_LOGIN, false);
  }
}
