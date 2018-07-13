package com.chatapp.ipme.chatapp.ui.contact;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.chatapp.ipme.chatapp.model.User;

import java.util.List;

public class ContactViewModel extends ViewModel {
  private MutableLiveData<List<User>> user;

  public LiveData<List<User>> getUsers() {
    if (user == null) {
      user = new MutableLiveData<>();
      loadContacts();
    }
    return user;
  }

  private void loadContacts() {
  }
}


