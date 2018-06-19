package com.chatapp.ipme.chatapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.adapter.MessageAdapter;
import com.chatapp.ipme.chatapp.interfaces.NetworkController;
import com.chatapp.ipme.chatapp.model.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        View rootView =  inflater.inflate(R.layout.fragment_chat, container, false);

        messageInput = rootView.findViewById(R.id.messageInput);

        recyclerView = rootView.findViewById(R.id.chat_recyclerView);
        recyclerView.setHasFixedSize(true);

        initializeData();

        adapter = new MessageAdapter(getContext(), messageList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }


    public void initializeData() {
        RequestQueue requestQueue = NetworkController.getInstance(getContext()).getRequestQueue();

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener <JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray ja = response.getJSONArray("users");
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject jsonObject = ja.getJSONObject(i);
                                Message message = Message.parse(jsonObject);
                                messageList.add(message);
                                linearLayoutManager = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(linearLayoutManager);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        requestQueue.add(jor);
    }
}
