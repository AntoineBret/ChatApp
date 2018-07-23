package com.chatapp.ipme.chatapp.ui.roomDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailsFragment extends Fragment {

    public static RoomDetailsFragment newInstance() {
        return new RoomDetailsFragment();
    }

    private RecyclerView recyclerView;
    private RoomDetailsAdapter adapter;
    private List<Room> roomList = new ArrayList<>();

    private Integer roomID;
    private String userName;
    private Integer userID;
    private String interlocutorName;
    private Integer interlocutorID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_roomdetails, container, false);

        getRoomDetailsData();

        recyclerView = rootView.findViewById(R.id.roomdetails_recyclerView);
        recyclerView.setHasFixedSize(true);

        roomList = new ArrayList<>();
        adapter = new RoomDetailsAdapter(getContext(), roomList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void getRoomDetailsData() {

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            roomID = bundle.getInt("room_id");
            userName = bundle.getString("user_name");
            userID = bundle.getInt("user_id");
            interlocutorName = bundle.getString("interlocutor_name");
            interlocutorID = bundle.getInt("interlocutor_id");
        }
    }
}