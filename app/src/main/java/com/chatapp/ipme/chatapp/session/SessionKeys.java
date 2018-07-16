package com.chatapp.ipme.chatapp.session;

public enum SessionKeys {

    PREFS_NAME("User"),

    KEY_TOKEN("TokenKey"),
    KEY_ID("IdKey"),
    KEY_USERNAME("UsernameKey"),
    KEY_FIRSTNAME("FirstnameKey"),
    KEY_LASTNAME("LastnameKey"),
    KEY_EMAIL("EmailKey");

    private String key;

    SessionKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

//todo: for use
// SessionManager.putString(SessionKeys.TEST.getKey(), "I'm SessionManager");
//         helloWorld.setText(SessionManager.getString(SessionKeys.TEST.getKey(), "1"));
//         Log.d("MainActivity ", "onCreate: "+ SessionManager.getString(SessionKeys.TEST.getKey(), "1"));