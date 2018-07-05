package com.chatapp.ipme.chatapp.ui.Settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Settings;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private Context context;
    public List<Settings> settingsList;

    public SettingsAdapter(Context context, List<Settings> settingsList) {
        this.context = context;
        this.settingsList = settingsList;
    }

    @Override
    public SettingsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_settings, viewGroup, false);
        return new SettingsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SettingsAdapter.ViewHolder holder, int position) {
        Settings settings = settingsList.get(position);

        holder.settings_title.setText(settings.getSettingsTitle());
        Glide
                .with(context)
                .load(settings.getSettingsThumbnail())
                .into(holder.settings_thumbnail);
    }

    @Override
    public int getItemCount() {
        return settingsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView settings_title;
        public ImageView settings_thumbnail;

        public ViewHolder(View view) {
            super(view);

            settings_title =  view.findViewById(R.id.settings_title);
            settings_thumbnail =  view.findViewById(R.id.settings_thumbnail);

        }
    }
}
