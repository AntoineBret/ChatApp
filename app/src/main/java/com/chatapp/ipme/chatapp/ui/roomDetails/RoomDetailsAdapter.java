package com.chatapp.ipme.chatapp.ui.roomDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Message;
import com.chatapp.ipme.chatapp.session.Chatapp;

import java.util.Date;
import java.util.List;

public class RoomDetailsAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private Context context;
    public List<Message> messageList;
    private LayoutInflater inflater = null;


    public RoomDetailsAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<Message> messageList) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);

        Integer serverUserID = message.getUser().getID();
        Integer currentUserID = Chatapp.getCurrentUserID();

        if (serverUserID.equals(currentUserID)) {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        } else {
            return VIEW_TYPE_MESSAGE_SENT;
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

        private TextView userNameText;
        private TextView userMessageText;
        private TextView userDateText;

        public UserThreadHolder(View view) {
            super(view);
            userNameText = itemView.findViewById(R.id.tv_user_name);
            userMessageText = itemView.findViewById(R.id.tv_user_message);
            userDateText = itemView.findViewById(R.id.tv_user_date);
        }

        void bind(Message message) {
            userNameText.setText(message.getUser().getUsername());
            userMessageText.setText(message.getMessageContent());
            Date user_date = new Date(message.getCreatedAt()); //for timestamp
            userDateText.setText(user_date.toString());
        }
    }

    private class InterlocutorThreadHolder extends RecyclerView.ViewHolder {

        private TextView interlocutorNameText;
        private TextView interlocutorMessageText;
        private TextView interlocutorDateText;

        public InterlocutorThreadHolder(View view) {
            super(view);
            interlocutorNameText = itemView.findViewById(R.id.tv_interlocutor_name);
            interlocutorMessageText = itemView.findViewById(R.id.tv_interlocutor_message);
            interlocutorDateText = itemView.findViewById(R.id.tv_interlocutor_date);
        }

        void bind(Message message) {
            interlocutorNameText.setText(message.getUser().getUsername());
            interlocutorMessageText.setText(message.getMessageContent());
            Date interlocutor_date = new Date(message.getCreatedAt());
            interlocutorDateText.setText(interlocutor_date.toString());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}