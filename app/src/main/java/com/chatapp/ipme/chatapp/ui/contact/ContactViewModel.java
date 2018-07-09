package com.chatapp.ipme.chatapp.ui.contact;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.chatapp.ipme.chatapp.model.Contact;

import java.util.List;

public class ContactViewModel extends ViewModel {
  private MutableLiveData<List<Contact>> contacts;

  public LiveData<List<Contact>> getUsers() {
    if (contacts == null) {
        contacts = new MutableLiveData<>();
      loadContacts();
    }
    return contacts;
  }

  private void loadContacts() {
  }
}


