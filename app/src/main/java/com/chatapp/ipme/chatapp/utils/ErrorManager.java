package com.chatapp.ipme.chatapp.utils;

import android.util.Log;

import java.net.ConnectException;

import io.reactivex.Observer;

import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.MESSAGE_BAD_REQUEST;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.MESSAGE_CONNECT_EXCEPTION;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.MESSAGE_FORBITTEN;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.MESSAGE_NOT_FOUND;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.MESSAGE_SERVER_ERROR;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.MESSAGE_UNAUTHORIZED;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_BAD_REQUEST;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_FORBITTEN;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_NOT_FOUND;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_SERVER_ERROR;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_UNAUTHORIZED;

public abstract class ErrorManager<T> implements Observer<T> {
    private AlertDialogManager alert;

    @Override
    public void onError(Throwable e) {
        Log.e("ChatApp", getMessage(e));
    }

    public String getMessage(Throwable e) {
        if (NetworkUtil.isHttpStatusCode(e, STATUS_BAD_REQUEST)) {
            return MESSAGE_BAD_REQUEST;
        } else if (NetworkUtil.isHttpStatusCode(e, STATUS_UNAUTHORIZED)) {
            return MESSAGE_UNAUTHORIZED;
        } else if (NetworkUtil.isHttpStatusCode(e, STATUS_FORBITTEN)) {
            return MESSAGE_FORBITTEN;
        } else if (NetworkUtil.isHttpStatusCode(e, STATUS_NOT_FOUND)) {
            return MESSAGE_NOT_FOUND;
        } else if (NetworkUtil.isHttpStatusCode(e, STATUS_SERVER_ERROR)) {
            return MESSAGE_SERVER_ERROR;
        } else if (e instanceof ConnectException) {
            return MESSAGE_CONNECT_EXCEPTION;
        } else
            return e.toString();
    }
}
