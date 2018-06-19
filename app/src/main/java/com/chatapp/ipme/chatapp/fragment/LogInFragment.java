package com.chatapp.ipme.chatapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;

public class LogInFragment extends Fragment {

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    private Toolbar toolbar;
    private TextView tvLogDescribe;
    private TextView tvPasswordDescribe;
    private EditText inputLog;
    private EditText inputPassword;
    private Button buttonLog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        tvLogDescribe = rootView.findViewById(R.id.tvLogDescribe);
        tvPasswordDescribe = rootView.findViewById(R.id.tvPasswordDescribe);
        inputLog = rootView.findViewById(R.id.inputLog);
        inputPassword = rootView.findViewById(R.id.inputPassword);
        buttonLog = rootView.findViewById(R.id.buttonLog);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setTitle("Login");

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = ContactFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
            }
        });
    }
}