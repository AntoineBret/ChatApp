package com.chatapp.ipme.chatapp.ui.profile;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Profile;
import com.chatapp.ipme.chatapp.session.Chatapp;

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
  private FragmentTransaction fragmentTransaction;
  private ProfileFragment profileFragment;

  //Variables
  private String username;
  private String firstname;
  private String lastname;
  private String email;
  private String birthdayDate;
  private String password;

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
    username = Chatapp.getCurrentUserName();
    firstname = Chatapp.getCurrentUserFirstname();
    lastname = Chatapp.getCurrentUserLastname();
    email = Chatapp.getCurrentUserEmail();
    birthdayDate = Chatapp.getCurrentUserBirthday();

    int[] covers = new int[]{
      R.drawable.ic_action_modify,
      R.drawable.ic_action_modify,
      R.drawable.ic_action_modify,
      R.drawable.ic_action_modify,
      R.drawable.ic_action_modify,
    };

    profileList.add(new Profile("Username", username, covers[0]));
    profileList.add(new Profile("Firstname", firstname, covers[1]));
    profileList.add(new Profile("Lastname", lastname, covers[2]));
    profileList.add(new Profile("Email", email, covers[3]));
    profileList.add(new Profile("Birthday", birthdayDate, covers[4]));

    adapter.setData(profileList);
  }
}
