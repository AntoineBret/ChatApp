package com.chatapp.ipme.chatapp.ui.contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    public List<Contact> contactList;
    private LayoutInflater inflater = null;


    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<Contact> contactList) {
        this.contactList = contactList;
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
        holder.setIsRecyclable(false);
        Contact contact = contactList.get(i);
        holder
                .username
                .setText(contact.getUsername());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username;

        public ViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.user_title);
        }
    }
}
