package com.chatapp.ipme.chatapp.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;

public class SelectUsersFragment extends Fragment {

    public static SelectUsersFragment newInstance() {
        return new SelectUsersFragment();
    }

    private FloatingActionButton floatingActionButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selectusers, container, false);

        floatingActionButton = rootView.findViewById(R.id.selectUsersFab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = ChatFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
            }
        });

        return rootView;
    }
}