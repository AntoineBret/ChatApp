package com.chatapp.ipme.chatapp.session;

public interface ISessionCreator {
    default void createSessionData(String token, Integer id, String username, String firstname, String lastname, String email) {
        SessionManager.putString(SessionKeys.KEY_TOKEN.getKey(), token);
        SessionManager.putInt(SessionKeys.KEY_ID.getKey(), id);
        SessionManager.putString(SessionKeys.KEY_USERNAME.getKey(), username);
        SessionManager.putString(SessionKeys.KEY_FIRSTNAME.getKey(), firstname);
        SessionManager.putString(SessionKeys.KEY_LASTNAME.getKey(), lastname);
        SessionManager.putString(SessionKeys.KEY_EMAIL.getKey(), email);
    }
}
