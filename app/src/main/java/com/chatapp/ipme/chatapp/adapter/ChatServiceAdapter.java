package com.chatapp.ipme.chatapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;

import java.util.List;


public class ChatServiceAdapter extends RecyclerView.Adapter<ChatServiceAdapter.ViewHolder> {

  private Context context;
  public List<Room> roomList;
  private LayoutInflater inflater = null;


  public ChatServiceAdapter(Context context, List<Room> roomList) {
    this.context = context;
    this.roomList = roomList;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public ChatServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    this.context = viewGroup.getContext();
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_roomdetails, viewGroup, false);
    return new ChatServiceAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ChatServiceAdapter.ViewHolder holder, final int i) {
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
