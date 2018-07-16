package com.chatapp.ipme.chatapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.chatapp.ipme.chatapp.ConnectToServiceActivity;
import com.chatapp.ipme.chatapp.api.Constants;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class SessionManager {
    public int PRIVATE_MODE = 0;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context mContext;

    private static String PREF_NAME;
    private static String IS_LOGIN = "isLoggedIn";

    public static String KEY_TOKEN;
    public static String KEY_ID;
    public static String KEY_USERNAME;
    public static String KEY_FIRSTNAME;
    public static String KEY_LASTNAME;
    public static String KEY_EMAIL;
//  public static final String KEY_AVATAR;
//  public static final String KEY_STATUS;
//  public static final String REGISTRATION_COMPLETE;

    public SessionManager(Context context) {
        mContext = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }

    public boolean createLoginSession(HashMap<String, Object> session) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_TOKEN, String.valueOf(session.get(Constants.SESSION_KEY_TOKEN)));
        editor.putInt(KEY_ID, Integer.parseInt(String.valueOf(session.get(Constants.SESSION_KEY_ID))));
        editor.putString(KEY_USERNAME, String.valueOf(session.get(Constants.SESSION_KEY_USERNAME)));
        editor.putString(KEY_FIRSTNAME, String.valueOf(session.get(Constants.SESSION_KEY_FIRSTNAME)));
        editor.putString(KEY_LASTNAME, String.valueOf(session.get(Constants.SESSION_KEY_LASTNAME)));
        editor.putString(KEY_EMAIL, String.valueOf(session.get(Constants.SESSION_KEY_EMAIL)));
//    editor.putString(KEY_AVATAR, String.valueOf(session.get(KEY_AVATAR)));
//    editor.putString(KEY_STATUS, String.valueOf(session.get(KEY_STATUS)));

        return editor.commit();
    }

    public boolean setSessionData(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    public boolean setSessionData(String key, boolean value) {
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean setSessionData(String key, int value) {
        editor.putInt(key, value);
        return editor.commit();
    }

    public String getSessionData(String key, String defValue) {
        return pref.getString(key, defValue);
    }

    public boolean getSessionData(String key, boolean defValue) {
        return pref.getBoolean(key, defValue);
    }

    public int getSessionData(String key, int defValue) {
        return pref.getInt(key, defValue);
    }

    public boolean isMe(int id) {
        return id != 0 && getSessionData(SessionManager.KEY_ID, 0) == id;
    }

    public boolean isMe(String username) {
        return getSessionData(SessionManager.KEY_ID, "").equals(username);
    }

    public HashMap<String, Object> getUserDetails() {
        HashMap<String, Object> user = new HashMap<>();
        user.put(KEY_TOKEN, pref.getString(Constants.SESSION_KEY_TOKEN, null));
        user.put(KEY_ID, pref.getInt(Constants.SESSION_KEY_ID, 0));
        user.put(KEY_USERNAME, pref.getString(Constants.SESSION_KEY_USERNAME, null));
        user.put(KEY_FIRSTNAME, pref.getString(Constants.SESSION_KEY_FIRSTNAME, null));
        user.put(KEY_LASTNAME, pref.getString(Constants.SESSION_KEY_LASTNAME, null));
        user.put(KEY_EMAIL, pref.getString(Constants.SESSION_KEY_EMAIL, null));
//    user.put(KEY_AVATAR, pref.getString(KEY_AVATAR, null));
//    user.put(KEY_STATUS, pref.getString(KEY_STATUS, null));
        return user;
    }

    public Map<String, ?> getAllSession() {
        return pref.getAll();
    }

    public int getSessionSize() {
        return pref.getAll().size();
    }

    public boolean logoutUser() {
        editor.remove(IS_LOGIN);
        editor.remove(Constants.SESSION_KEY_TOKEN);
        editor.remove(Constants.SESSION_KEY_ID);
        editor.remove(Constants.SESSION_KEY_USERNAME);
        editor.remove(Constants.SESSION_KEY_FIRSTNAME);
        editor.remove(Constants.SESSION_KEY_LASTNAME);
        editor.remove(Constants.SESSION_KEY_EMAIL);
//    editor.remove(KEY_AVATAR);
//    editor.remove(KEY_STATUS);
        editor.apply();
        editor.commit();
        Intent intent = new Intent(mContext, ConnectToServiceActivity.class);
        mContext.startActivity(intent);

        return editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

}
