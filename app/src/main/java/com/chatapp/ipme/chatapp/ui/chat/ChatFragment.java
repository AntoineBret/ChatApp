package com.chatapp.ipme.chatapp.ui.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Message> messageList = new ArrayList<Message>();
    private EditText messageInput;
    private static final String URL = "http://192.168.1.54:8080/users";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        messageInput = rootView.findViewById(R.id.messageInput);

        recyclerView = rootView.findViewById(R.id.chat_recyclerView);
        recyclerView.setHasFixedSize(true);

        initializeData();

        adapter = new MessageAdapter(getContext(), messageList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }


    public void initializeData() {
    }
}
