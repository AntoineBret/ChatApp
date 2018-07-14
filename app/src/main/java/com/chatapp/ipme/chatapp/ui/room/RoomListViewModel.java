package com.chatapp.ipme.chatapp.ui.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.chatapp.ipme.chatapp.model.Room;

import java.util.List;


public class RoomListViewModel extends ViewModel {

  private MutableLiveData<List<Room>> rooms;

  public LiveData<List<Room>> getRooms() {
    if (rooms == null) {
      rooms = new MutableLiveData<>();
      loadRooms();
    }
    return rooms;
  }

  private void loadRooms() {
  }
}
