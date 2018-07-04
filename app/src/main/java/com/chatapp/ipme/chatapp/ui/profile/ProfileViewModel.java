package com.chatapp.ipme.chatapp.ui.profile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.chatapp.ipme.chatapp.model.Profile;

import java.util.List;


public class ProfileViewModel extends ViewModel {

    private MutableLiveData<List<Profile>> profile;

    public LiveData<List<Profile>> getProfile() {
        if (profile == null) {
            profile = new MutableLiveData<>();
            loadProfile();
        }
        return profile;
    }

    private void loadProfile() {
    }
}

