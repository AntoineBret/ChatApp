package com.chatapp.ipme.chatapp.ui.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Profile;
import com.chatapp.ipme.chatapp.model.User;

import java.util.List;

class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    public List<Profile> profileList;

    public ProfileAdapter(Context context, List<Profile> profileList) {
        this.context = context;
        this.profileList = profileList;
    }

    public void setData(List<Profile> profileList) {
        this.profileList = profileList;
        notifyDataSetChanged();
    }

    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_profil, viewGroup, false);
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {
        Profile profile = profileList.get(position);

        holder.profile_item.setText(profile.getProfileItem());
        holder.profile_data.setText(profile.getProfileData());
        Glide.with(context).load(profile.getProfileThumbnail()).into(holder.modify_icon);

    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView profile_item;
        public TextView profile_data;
        public ImageView modify_icon;

        public ViewHolder(View view) {
            super(view);

            profile_item = view.findViewById(R.id.profile_item);
            profile_data = view.findViewById(R.id.profile_data);
            modify_icon = view.findViewById(R.id.modify_icon);
        }
    }
}