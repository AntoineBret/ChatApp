package com.chatapp.ipme.chatapp.ui.room;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.DisplayRoom;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {

  private Context context;
  public List<DisplayRoom> displayRoomList;

  public RoomListAdapter(Context context, List<DisplayRoom> displayRoomList) {
    this.context = context;
    this.displayRoomList = displayRoomList;
  }

  public void setData(List<DisplayRoom> displayRoomList) {
    this.displayRoomList = displayRoomList;
    notifyDataSetChanged();
  }

  @Override
  public RoomListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_room, viewGroup, false);
    return new RoomListAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RoomListAdapter.ViewHolder holder, int position) {
    DisplayRoom displayRoom = displayRoomList.get(position);
    holder.room_title.setText(displayRoom.getName());
  }

  @Override
  public int getItemCount() {
    return displayRoomList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView room_title;

    public ViewHolder(View view) {
      super(view);

      room_title =  view.findViewById(R.id.room_title);
    }
  }
}
