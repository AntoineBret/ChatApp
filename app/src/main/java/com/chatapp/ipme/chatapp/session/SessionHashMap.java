package com.chatapp.ipme.chatapp.session;

import java.util.HashMap;
import java.util.Map;

public class SessionHashMap {

    public static Map<String, Object> newUserDataMap = new HashMap<>();

    public static Map<String, Object> getNewUserDataMap() {
        return newUserDataMap;
    }
}
