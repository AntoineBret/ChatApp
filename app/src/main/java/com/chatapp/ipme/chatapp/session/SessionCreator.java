package com.chatapp.ipme.chatapp.session;

public interface SessionCreator {
  default void createSessionData(String token, Integer id, String username, String password, String firstname, String lastname, String birthday, String email) {
    SessionManager.putString(SessionKeys.KEY_TOKEN.getKey(), token);
    SessionManager.putInt(SessionKeys.KEY_ID.getKey(), id);
    SessionManager.putString(SessionKeys.KEY_USERNAME.getKey(), username);
    SessionManager.putString(SessionKeys.KEY_PASSWORD.getKey(), password);
    SessionManager.putString(SessionKeys.KEY_FIRSTNAME.getKey(), firstname);
    SessionManager.putString(SessionKeys.KEY_LASTNAME.getKey(), lastname);
    SessionManager.putString(SessionKeys.KEY_BIRTHDAY.getKey(), birthday);
    SessionManager.putString(SessionKeys.KEY_EMAIL.getKey(), email);
  }

  static void editUsername(String username) {
    SessionManager.putString(SessionKeys.KEY_USERNAME.getKey(), username);
  }

  static void editPassword(String password) {
    SessionManager.putString(SessionKeys.KEY_PASSWORD.getKey(), password);
  }

  static void editFirstname(String firstname) {
    SessionManager.putString(SessionKeys.KEY_FIRSTNAME.getKey(), firstname);
  }

  static void editLastname(String lastname) {
    SessionManager.putString(SessionKeys.KEY_LASTNAME.getKey(), lastname);
  }

  static void editBirthday(String birthday) {
    SessionManager.putString(SessionKeys.KEY_BIRTHDAY.getKey(), birthday);
  }

  static void editEmail(String email) {
    SessionManager.putString(SessionKeys.KEY_EMAIL.getKey(), email);
  }
}
