package com.chatapp.ipme.chatapp.ui.Settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.adapter.RecyclerItemClickListener;
import com.chatapp.ipme.chatapp.model.Settings;
import com.chatapp.ipme.chatapp.ui.logout.LogOutFragment;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SettingsAdapter adapter;
    private List<Settings> settingsList;


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.parameter_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        recyclerView = rootView.findViewById(R.id.settings_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        settingsList = new ArrayList<>();
        initializeSettings();
        adapter = new SettingsAdapter(getContext(), settingsList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), (view, position) -> {
                    switch (position) {
                        case 0:
                            Fragment f = LogOutFragment.newInstance();
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.parameter_frame_container, f)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        case 1:
                            Fragment y = LogOutFragment.newInstance();
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.parameter_frame_container, y)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                    }
                }));


        return rootView;
    }

    private void initializeSettings() {
        int[] covers = new int[]{
                R.drawable.ic_account,
                R.drawable.ic_disconect,};

        settingsList.add(new Settings(covers[0], "LogOut"));
        settingsList.add(new Settings(covers[1], "test2"));
    }
}
