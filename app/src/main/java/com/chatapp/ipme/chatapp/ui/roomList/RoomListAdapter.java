package com.chatapp.ipme.chatapp.ui.roomList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.ChatServiceActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;

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
      Intent chatServiceIntent = new Intent(context, ChatServiceActivity.class);
      chatServiceIntent.putExtra("room_id", room.getId());
      chatServiceIntent.putExtra("interlocutor_name", room.getName());
      context.startActivity(chatServiceIntent);
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
