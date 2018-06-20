package com.chatapp.ipme.chatapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Message;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    public List<Message> messageList;
    private LayoutInflater inflater = null;


    public ContactAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_contact, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, final int i) {
        holder.setIsRecyclable(false);
        final Message message = messageList.get(i);
   
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView pseudo;

        public ViewHolder(View view) {

            super(view);

            id = (TextView) view.findViewById(R.id.chat_id);
            pseudo = (TextView) view.findViewById(R.id.chat_pseudo);
        }
    }
}