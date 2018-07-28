package com.chatapp.ipme.chatapp.ui.newContact;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.ContactActivity;
import com.chatapp.ipme.chatapp.R;

public class NewContactFragment extends Fragment{

    public static NewContactFragment newInstance() {
        return new NewContactFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_contact, container, false);

      ((ContactActivity) getActivity()).getSupportActionBar().setTitle(R.string.new_contact_toolbar);

      return rootView;
    }
}
