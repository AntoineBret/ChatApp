package com.chatapp.ipme.chatapp.ui.settings;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatapp.ipme.chatapp.ConnectToServiceActivity;
import com.chatapp.ipme.chatapp.ParameterActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.adapter.RecyclerItemClickListener;
import com.chatapp.ipme.chatapp.model.Settings;
import com.chatapp.ipme.chatapp.session.Chatapp;
import com.chatapp.ipme.chatapp.session.SessionManager;
import com.chatapp.ipme.chatapp.ui.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SettingsAdapter adapter;
    private List<Settings> settingsList;

    private RelativeLayout displayUserDetails;
    private ImageView displayThumbnail;
    private TextView displayUsername;
    private Drawable default_thumbnail;
    private Integer userThumbnail;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        ((ParameterActivity) getActivity()).getSupportActionBar().setTitle(R.string.parameter_toolbar);

        displayUsername = rootView.findViewById(R.id.settingsUsername);
        displayThumbnail = rootView.findViewById(R.id.settingsThumbnail);

        default_thumbnail = getResources().getDrawable(R.drawable.default_thumbnail);
        userThumbnail = Chatapp.getCurrentUserThumbnail();

        getUserLogDetails();

        displayUserDetails = rootView.findViewById(R.id.rl_container_userProfile);
        displayUserDetails.setOnClickListener(view -> {
            Fragment f = ProfileFragment.newInstance();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.parameter_frame_container, f)
                    .addToBackStack(null)
                    .commit();
        });

        settingsList = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.settings_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        adapter = new SettingsAdapter(getContext(), settingsList);
        recyclerView.setAdapter(adapter);

        initializeSettings();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), (view, position) -> {
                    switch (position) {
                        case 2:
                            SessionManager.removeKeys();
                            Intent disconect = new Intent(getContext(), ConnectToServiceActivity.class);
                            startActivity(disconect);
                            break;
                    }
                }));


        return rootView;
    }

    private void initializeSettings() {
        int[] covers = new int[]{
                R.drawable.ic_notification,
                R.drawable.ic_account,
                R.drawable.ic_disconect,
        };

        settingsList.add(new Settings("Notifications //todo", covers[0]));
        settingsList.add(new Settings("Compte //todo", covers[1]));
        settingsList.add(new Settings("Déconnexion", covers[2]));

        adapter.notifyDataSetChanged();
    }

    private void getUserLogDetails() {
        Object name = Chatapp.getCurrentUserName();
        if (name == null) {
            displayUsername.setText("Aucun utilisateur log");
        } else {
            displayUsername.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        }

        if (displayThumbnail.getDrawable() == null && userThumbnail == 0) {
            //set default thumbnail
            Glide.with(getContext()).load(default_thumbnail).into(displayThumbnail);
        } else {
            Glide.with(getContext()).load(userThumbnail).into(displayThumbnail);
        }
    }
}