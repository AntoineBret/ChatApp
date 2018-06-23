package com.chatapp.ipme.chatapp.ui.room;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

  private Context context;
  public List<Room> roomList;
  private LayoutInflater inflater = null;


  public RoomAdapter(Context context, List<Room> roomList) {
    this.context = context;
    this.roomList = roomList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public RoomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_room, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RoomAdapter.ViewHolder holder, final int i) {
    holder.setIsRecyclable(false);
    final Room room = roomList.get(i);

  }

  @Override
  public int getItemCount() {
    return roomList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {


    public ViewHolder(View view) {

      super(view);

    }
  }
}
