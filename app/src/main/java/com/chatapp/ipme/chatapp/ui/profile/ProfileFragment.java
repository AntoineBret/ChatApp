package com.chatapp.ipme.chatapp.ui.profile;

import android.arch.lifecycle.ViewModelProviders;
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
import com.chatapp.ipme.chatapp.model.Profile;
import com.chatapp.ipme.chatapp.session.SessionKeys;
import com.chatapp.ipme.chatapp.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ProfileAdapter adapter;
    private List<Profile> profileList;

    //Variables
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String birthdayDate;
    private String password;;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        ProfileViewModel model = ViewModelProviders.of(this).get(ProfileViewModel.class);
        model.getProfile().observe(this, profile -> {
        });

        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.profile_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        recyclerView = rootView.findViewById(R.id.profile_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        profileList = new ArrayList<>();
        adapter = new ProfileAdapter(getContext(), profileList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        initializeProfile();

        return rootView;
    }

    private void initializeProfile() {
        username = SessionManager.getString(SessionKeys.KEY_USERNAME.getKey(), null);
        firstname = SessionManager.getString(SessionKeys.KEY_FIRSTNAME.getKey(), null);
        lastname = SessionManager.getString(SessionKeys.KEY_LASTNAME.getKey(), null);
        email = SessionManager.getString(SessionKeys.KEY_EMAIL.getKey(), null);
        birthdayDate = SessionManager.getString(SessionKeys.KEY_BIRTHDAY.getKey(), null);
//         password = SessionManager.getString(SessionKeys.KEY_PASSWORD.getKey(), null);

        profileList.add(new Profile("Username", username));
        profileList.add(new Profile("Firstname", firstname));
        profileList.add(new Profile("Lastname", lastname));
        profileList.add(new Profile("Email", email));
        profileList.add(new Profile("Birthday", birthdayDate));
//        profileList.add(new Profile("Password", password));

        adapter.setData(profileList);
    }
}