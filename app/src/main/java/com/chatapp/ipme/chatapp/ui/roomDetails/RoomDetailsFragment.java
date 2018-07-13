package com.chatapp.ipme.chatapp.ui.roomDetails;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.DisplayRoom;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailsFragment extends Fragment {

    public static RoomDetailsFragment newInstance() {
        return new RoomDetailsFragment();
    }

    private RecyclerView recyclerView;
    private RoomDetailsAdapter adapter;
    private List<DisplayRoom> displayRoomList = new ArrayList<>();
    private ApiEndPointInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_roomdetails, container, false);

        //recover room ID for send data
        Integer roomID = getArguments().getInt("room_id");

        recyclerView = rootView.findViewById(R.id.roomdetails_recyclerView);
        recyclerView.setHasFixedSize(true);

        displayRoomList = new ArrayList<>();
        adapter = new RoomDetailsAdapter(getContext(), displayRoomList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}

