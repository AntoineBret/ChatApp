package com.chatapp.ipme.chatapp.ui.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Profile;
import com.chatapp.ipme.chatapp.session.Chatapp;
import com.chatapp.ipme.chatapp.utils.EditTextDatePicker;

import java.util.HashMap;
import java.util.List;

class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    public List<Profile> profileList;
    private int i = 0;
    public static HashMap<String, Object> newUserDataMap = new HashMap<>();
    private String profileItemValue;
    private String profileDataNewValue;

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
        profileItemValue = profile.getProfileItem();
        String profileDataValue = profile.getProfileData();

        holder.profile_item.setText(profileItemValue);
        holder.profile_data_tv.setText(profileDataValue);

        //initialize icon edit at first time
        if (holder.edit_icon.getDrawable() == null) {
            Glide.with(context).load(R.drawable.ic_action_edit).into(holder.edit_icon);
        }

        //replace classic edittext to date picker if user want to change his birthday date
        if (profileItemValue.equals("Birthday")) {
            //dismiss keyboard for display date picker properly
            holder.edit_data_edittext.setFocusable(false);
            new EditTextDatePicker(context, holder.edit_data_edittext);
        }

        holder.edit_icon.setOnClickListener(view -> {
            if (i == 0 /* first click  = change icon + get editText*/) {
                //if icon is clicked once, remplace textView by editText & change icon
                holder.switcher.showNext();
                Glide.with(context).load(R.drawable.ic_action_validate).into(holder.edit_icon);
                //Display hint to editText
                holder.edit_data_edittext.setHint(profile.getProfileData());
                //incrementing state
                i++;

            } else if (i == 1 /* second click = */) {
                //if icon is clicked again, display previous icon & remplace editText by textView
                holder.switcher.showPrevious();
                Glide.with(context).load(R.drawable.ic_action_edit).into(holder.edit_icon);
                //get new data & display them to user only if something has changed
                profileDataNewValue = holder.edit_data_edittext.getText().toString();
                if (!profileDataNewValue.equals(profileDataValue) && !profileDataNewValue.equals("")) {
                    holder.profile_data_tv.setText(profileDataNewValue);
                }
                //prepare data before sending to the api
                prepareNewData();

                ProfileFragment.sendNewUserData();
                //return to default state
                i = 0;
                }
        });
    }

    private void prepareNewData() {
        //backend need id + username + password for authorize data edit ><
        // todo : change backend logic
        newUserDataMap.put("id", Chatapp.getCurrentUserID());
        newUserDataMap.put("username", Chatapp.getCurrentUserName());
        newUserDataMap.put("password", Chatapp.getCurrentPassword());
        //Send new data for change to backend
        newUserDataMap.put((profileItemValue.toLowerCase()), profileDataNewValue);
        //hashmap is now configured to be recover in the fragment of the adapter
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView profile_item;
        public TextView profile_data_tv;
        public EditText edit_data_edittext;
        public ImageView edit_icon;
        public ViewSwitcher switcher;

        public ViewHolder(View view) {
            super(view);

            switcher = view.findViewById(R.id.profile_switcher);
            profile_item = view.findViewById(R.id.profile_item);
            profile_data_tv = view.findViewById(R.id.profile_data_textview);
            edit_data_edittext = switcher.findViewById(R.id.profile_data_edittext);
            edit_icon = view.findViewById(R.id.modify_icon);
        }
    }
}
