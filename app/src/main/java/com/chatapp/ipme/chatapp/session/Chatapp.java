package com.chatapp.ipme.chatapp.session;

import android.content.Context;

public class Chatapp {
    
    private static Context context;
    private static SessionManager.Builder BUILDER;

    public Chatapp(Context context) {
        setBuilder(context);
    }

    public static void setBuilder(Context context) {
        Chatapp.context = context;

        BUILDER = new SessionManager.Builder()
                .setPrefsName(SessionKeys.PREFS_NAME.getKey())
                .setContext(context);

        BUILDER.build();
    }

    public static Integer getCurrentUserID() {
        Integer currentUserID = SessionManager.getInt(SessionKeys.KEY_ID.getKey(), 0);
        return currentUserID;
    }

    public static String getCurrentUserName() {
        String currentUserUsername = SessionManager.getString(SessionKeys.KEY_USERNAME.getKey(), null);
        return currentUserUsername;
    }

    public static String getCurrentUserFirstname() {
        String currentUserFirstname = SessionManager.getString(SessionKeys.KEY_FIRSTNAME.getKey(), null);
        return currentUserFirstname;
    }

    public static String getCurrentUserLastname() {
        String currentUserLastname = SessionManager.getString(SessionKeys.KEY_LASTNAME.getKey(), null);
        return currentUserLastname;
    }

    public static String getCurrentUserBirthday() {
        String currentUserBirthday = SessionManager.getString(SessionKeys.KEY_BIRTHDAY.getKey(), null);
        return currentUserBirthday;
    }

    public static String getCurrentUserEmail() {
        String currentUserEmail = SessionManager.getString(SessionKeys.KEY_EMAIL.getKey(), null);
        return currentUserEmail;
    }

    public static String getCurrentToken() {
        String currentUserToken = SessionManager.getString(SessionKeys.KEY_TOKEN.getKey(), null);
        return currentUserToken;
    }
}
