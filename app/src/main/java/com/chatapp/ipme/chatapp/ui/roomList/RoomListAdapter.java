package com.chatapp.ipme.chatapp.ui.roomList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.ui.roomDetails.RoomDetailsFragment;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {

  private Context context;
  public List<Room> roomList;

  public RoomListAdapter(Context context, List<Room> roomList) {
    this.context = context;
    this.roomList = roomList;
  }

  public void setData(List<Room> roomList) {
    this.roomList = roomList;
    notifyDataSetChanged();
  }

  @Override
  public RoomListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_room_list, viewGroup, false);
    return new RoomListAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RoomListAdapter.ViewHolder holder, int position) {
    Room room = roomList.get(position);
    holder.room_title.setText(room.getName());

    holder.room_title.setOnClickListener(v -> {
      Integer roomID = room.getId();

      Bundle bundle = new Bundle();
      bundle.putInt("room_id", roomID);
      Fragment roomDetails = RoomDetailsFragment.newInstance();
      roomDetails.setArguments(bundle);

      ((AppCompatActivity) context).getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.login_frame_container, roomDetails)
        .commit();
    });
  }

  @Override
  public int getItemCount() {
    return roomList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView room_title;

    public ViewHolder(View view) {
      super(view);

      room_title = view.findViewById(R.id.room_title);
    }
  }
}
