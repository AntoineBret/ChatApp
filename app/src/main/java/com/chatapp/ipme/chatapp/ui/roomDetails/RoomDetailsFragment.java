package com.chatapp.ipme.chatapp.ui.roomDetails;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.ui.room.RoomFragment;
import com.chatapp.ipme.chatapp.ui.room.RoomViewModel;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailsFragment extends Fragment {

    public static RoomDetailsFragment newInstance() {
        return new RoomDetailsFragment();
    }

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RoomDetailsAdapter adapter;
    private List<Room> roomList = new ArrayList<>();
    private String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_roomdetails, container, false);
        RoomDetailsViewModel model = ViewModelProviders.of(this).get(RoomDetailsViewModel.class);
        model.getRooms().observe(this, users -> {
        });

        Bundle arguments = getArguments();
        username = arguments.getString("user_name");

        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        recyclerView = rootView.findViewById(R.id.roomdetails_recyclerView);
        recyclerView.setHasFixedSize(true);

        initializeRoom();

        adapter = new RoomDetailsAdapter(getContext(), roomList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setTitle(username);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void initializeRoom() {
    }
}

