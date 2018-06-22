package com.chatapp.ipme.chatapp.ui.room;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.ui.contact.ContactViewModel;
import com.chatapp.ipme.chatapp.ui.message.MessageFragment;

public class SelectUsersFragment extends Fragment {

    public static SelectUsersFragment newInstance() {
        return new SelectUsersFragment();
    }

    private FloatingActionButton floatingActionButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selectusers, container, false);

        ViewModel viewModel = ViewModelProviders.of(this).get(SelectUsersViewModel.class);

        floatingActionButton = rootView.findViewById(R.id.selectUsersFab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = MessageFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
            }
        });

        return rootView;
    }
}