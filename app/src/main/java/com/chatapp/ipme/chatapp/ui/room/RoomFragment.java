package com.chatapp.ipme.chatapp.ui.room;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomFragment extends Fragment {

  public static RoomFragment newInstance() {
    return new RoomFragment();
  }

  private RecyclerView recyclerView;
  private RoomAdapter adapter;
  private List<Room> roomList = new ArrayList<>();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_room, container, false);
    RoomViewModel model = ViewModelProviders.of(this).get(RoomViewModel.class);
    model.getRooms().observe(this, users -> {
    });

    recyclerView = rootView.findViewById(R.id.room_recyclerView);
    recyclerView.setHasFixedSize(true);

    initializeRoom();

    adapter = new RoomAdapter(getContext(), roomList);
    recyclerView.setAdapter(adapter);

    return rootView;
  }

  private void initializeRoom() {
  }
}
