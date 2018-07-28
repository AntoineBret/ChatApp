package com.chatapp.ipme.chatapp.ui.newGroup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.ContactActivity;
import com.chatapp.ipme.chatapp.R;

public class NewGroupFragment extends Fragment {

  public static NewGroupFragment newInstance() {
    return new NewGroupFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_new_group, container, false);

    ((ContactActivity) getActivity()).getSupportActionBar().setTitle(R.string.new_group_toolbar);

    return rootView;
  }
}
