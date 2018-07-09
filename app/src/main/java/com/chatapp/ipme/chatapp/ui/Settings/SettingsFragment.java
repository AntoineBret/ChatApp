package com.chatapp.ipme.chatapp.ui.Settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.adapter.RecyclerItemClickListener;
import com.chatapp.ipme.chatapp.model.Settings;
import com.chatapp.ipme.chatapp.utils.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsFragment extends Fragment {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SettingsAdapter adapter;
    private List<Settings> settingsList;
    private SessionManager session;
    private ImageView displayThumbnail;
    private TextView displayUsername;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.parameter_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        displayThumbnail = rootView.findViewById(R.id.settingsThumbnail);
        displayUsername = rootView.findViewById(R.id.settingsUsername);

        getUserLogDetails();

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
                            session = new SessionManager(getContext());
                            session.logoutUser();
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
        Map<String, String> user = session.getUserDetails();
        String name = user.get(SessionManager.KEY_NAME);

        if (name == null) {
            displayUsername.setText("Aucun utilisateur log");
        } else
            displayUsername.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
    }
}
