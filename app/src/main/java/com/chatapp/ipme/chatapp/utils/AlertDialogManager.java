package com.chatapp.ipme.chatapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogManager {

  public void showAlertDialog(Context context, String title, String message, Boolean status) {

    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
    alertDialog.setTitle(title);
    alertDialog.setMessage(message);

    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
      "OK", (dialog, which) -> {
      });

    alertDialog.show();
  }
}
