package com.chatapp.ipme.chatapp.utils;

import java.net.ConnectException;

import io.reactivex.Observer;
import timber.log.Timber;

import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.ERROR_UNKNOWN;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.MESSAGE_BAD_REQUEST;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.MESSAGE_CONNECT_EXCEPTION;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.MESSAGE_FORBITTEN;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.MESSAGE_NOT_FOUND;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.MESSAGE_SERVER_ERROR;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.MESSAGE_UNAUTHORIZED;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.STATUS_BAD_REQUEST;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.STATUS_FORBITTEN;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.STATUS_NOT_FOUND;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.STATUS_SERVER_ERROR;
import static com.chatapp.ipme.chatapp.remote.Constants.httpcodes.STATUS_UNAUTHORIZED;

public abstract class ErrorManager<T> implements Observer<T> {

    AlertDialogManager alert;
    @Override
    public void onError(Throwable e) {
        if (NetworkUtil.isHttpStatusCode(e, STATUS_BAD_REQUEST)) {
            Timber.e(MESSAGE_BAD_REQUEST);
        } else if (NetworkUtil.isHttpStatusCode(e, STATUS_UNAUTHORIZED)) {
            Timber.e(MESSAGE_UNAUTHORIZED);
        } else if (NetworkUtil.isHttpStatusCode(e, STATUS_FORBITTEN)) {
            Timber.e(MESSAGE_FORBITTEN);
        } else if (NetworkUtil.isHttpStatusCode(e, STATUS_NOT_FOUND)) {
            Timber.e(MESSAGE_NOT_FOUND);
        } else if (NetworkUtil.isHttpStatusCode(e, STATUS_SERVER_ERROR)) {
            Timber.e(MESSAGE_SERVER_ERROR);
        } else if (e instanceof ConnectException) {
            Timber.e(MESSAGE_CONNECT_EXCEPTION);
        } else Timber.e(ERROR_UNKNOWN);
    }
}
