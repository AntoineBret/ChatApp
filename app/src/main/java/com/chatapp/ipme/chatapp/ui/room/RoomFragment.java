package com.chatapp.ipme.chatapp.ui.room;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.ContactActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;

import java.util.ArrayList;
import java.util.List;

public class RoomFragment extends Fragment {

    public static RoomFragment newInstance() {
        return new RoomFragment();
    }

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private RoomAdapter adapter;
    private List<Room> roomList;
    private ApiEndPointInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room, container, false);

        RoomViewModel model = ViewModelProviders.of(this).get(RoomViewModel.class);
        model.getRooms().observe(this, rooms -> {
        });

        recyclerView = rootView.findViewById(R.id.room_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        roomList = new ArrayList<>();
        adapter = new RoomAdapter(getContext(), roomList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        floatingActionButton = rootView.findViewById(R.id.contactsFab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ContactActivity.class);
            startActivity(intent);
        });

        // initializeRoom();

        return rootView;
    }

    private void initializeRoom() {
        //todo
        apiInterface = new ApiClient(getContext())
                .getClient()
                .create(ApiEndPointInterface.class);
    }
}