package com.chatapp.ipme.chatapp.ui.contact;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.HandleRoomActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.User;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    public List<User> userList;

    public ContactAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public void setData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contact, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, final int i) {
        User user = userList.get(i);
        holder.username.setText(user.getUsername());

        holder.username.setOnClickListener(v -> {
            String displayInterlocutorUsername = (user.getUsername());
            Integer displayInterlocutorID = (user.getID());

            Intent roomIntent = new Intent(context, HandleRoomActivity.class);
            //send selected "username" to next fragment
            roomIntent.putExtra("interlocutor_name", displayInterlocutorUsername);
            roomIntent.putExtra("interlocutor_id", displayInterlocutorID);
            context.startActivity(roomIntent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username;

        public ViewHolder(View view) {
            super(view);

            username = (TextView) view.findViewById(R.id.user_title);
        }
    }
}
