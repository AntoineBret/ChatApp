package com.chatapp.ipme.chatapp.session;

public enum SessionKeys {

    PREFS_NAME("User"),

    KEY_TOKEN("TokenKey"),
    KEY_ID("IdKey"),
    KEY_USERNAME("UsernameKey"),
    KEY_FIRSTNAME("FirstnameKey"),
    KEY_LASTNAME("LastnameKey"),
    KEY_EMAIL("EmailKey"),
    KEY_PASSWORD("PasswordKey"),
    KEY_BIRTHDAY("BirthdayKey");

    private String key;

    SessionKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
