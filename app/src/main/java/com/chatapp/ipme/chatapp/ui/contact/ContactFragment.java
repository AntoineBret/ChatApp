package com.chatapp.ipme.chatapp.ui.contact;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.ui.room.SelectUsersFragment;
import com.chatapp.ipme.chatapp.model.Message;

import java.util.ArrayList;
import java.util.List;


public class ContactFragment extends android.support.v4.app.Fragment {

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ContactAdapter adapter;
    private List<Message> messageList = new ArrayList<Message>();
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        floatingActionButton = rootView.findViewById(R.id.contactsFab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = SelectUsersFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
            }
        });
        recyclerView = rootView.findViewById(R.id.chat_recyclerView);
        recyclerView.setHasFixedSize(true);

        initializeContact();

        adapter = new ContactAdapter(getContext(), messageList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("Discussions");
    }

    private void initializeContact() {
    }
}
