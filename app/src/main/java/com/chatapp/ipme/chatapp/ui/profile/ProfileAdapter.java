package com.chatapp.ipme.chatapp.ui.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Profile;

import java.util.List;

class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    public List<Profile> profileList;

    public ProfileAdapter(Context context, List<Profile> profileList) {
        this.context = context;
        this.profileList = profileList;
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
        //todo
//        holder.room_title.setText(room.getName());
//        holder.room_subtitle.setText(room.getSubname());
//        Glide
//                .with(context)
//                .load(room.getThumbnail())
//                .into(holder.room_thumbnail);
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        public TextView room_title;
//        public TextView room_subtitle;
//        public ImageView room_thumbnail;

        public ViewHolder(View view) {
            super(view);

//            room_title = view.findViewById(R.id.room_title);
//            room_subtitle = view.findViewById(R.id.room_subtitle);
//            room_thumbnail = view.findViewById(R.id.room_thumbnail);
        }
    }
}