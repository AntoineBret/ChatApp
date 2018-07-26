package com.chatapp.ipme.chatapp.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.TimeZone;

import static com.chatapp.ipme.chatapp.adapter.DateFormat.getDateFormatted;

public class EditTextDatePicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    EditText editText;

    private int day;
    private int month;
    private int birthYear;
    private Context context;

    public EditTextDatePicker(Context context, EditText editText) {
        Activity act = (Activity) context;
        this.context = context;

        this.editText = editText;
        this.editText.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        birthYear = year;
        month = monthOfYear;
        day = dayOfMonth;
        updateDisplay();
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }

    // updates the date in the birth date EditText
    private void updateDisplay() {

        StringBuilder strBuilder = new StringBuilder();
        strBuilder
                .append(day)
                .append("-")
                .append(month + 1)  // Month is 0 based so add 1
                .append("-")
                .append(birthYear);

        editText.setText(getDateFormatted(strBuilder.toString()));
//        editText.setText(strBuilder.toString());
    }
}
