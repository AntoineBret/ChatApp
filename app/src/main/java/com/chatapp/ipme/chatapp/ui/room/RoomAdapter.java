package com.chatapp.ipme.chatapp.ui.room;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

  private Context context;
  public List<Room> roomList;

  public RoomAdapter(Context context, List<Room> roomList) {
    this.context = context;
    this.roomList = roomList;
  }

  @Override
  public RoomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_room, viewGroup, false);
    return new RoomAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RoomAdapter.ViewHolder holder, int position) {
    Room room = roomList.get(position);
    holder.room_title.setText(room.getName());
    holder.room_subtitle.setText(room.getSubname());
    Glide
      .with(context)
      .load(room.getThumbnail())
      .into(holder.room_thumbnail);
  }

  @Override
  public int getItemCount() {
    return roomList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView room_title;
    public TextView room_subtitle;
    public ImageView room_thumbnail;

    public ViewHolder(View view) {
      super(view);

      room_title =  view.findViewById(R.id.room_title);
      room_subtitle =  view.findViewById(R.id.room_subtitle);
      room_thumbnail =  view.findViewById(R.id.room_thumbnail);
    }
  }
}
