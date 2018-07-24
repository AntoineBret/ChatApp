package com.chatapp.ipme.chatapp.ui.roomDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Message;
import com.chatapp.ipme.chatapp.session.Chatapp;

import java.util.List;

import static com.chatapp.ipme.chatapp.utils.Constants.VIEW_TYPE_MESSAGE_RECEIVED;
import static com.chatapp.ipme.chatapp.utils.Constants.VIEW_TYPE_MESSAGE_SENT;

public class RoomDetailsAdapter extends RecyclerView.Adapter {

    private Context context;
    public List<Message> messageList;
    private LayoutInflater inflater = null;


    public RoomDetailsAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);

        if (message.getUser().getID().equals(Chatapp.getCurrentUserID())) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_thread, parent, false);
            return new UserThreadHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.interlocutor_thread, parent, false);
            return new InterlocutorThreadHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((UserThreadHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((InterlocutorThreadHolder) holder).bind(message);
        }
    }

    private class UserThreadHolder extends RecyclerView.ViewHolder {
        public UserThreadHolder(View view) {
            super(view);
        }
        void bind(Message message) {
        }
    }

    private class InterlocutorThreadHolder extends RecyclerView.ViewHolder {
        public InterlocutorThreadHolder(View view) {
            super(view);
        }
        void bind(Message message) {
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}