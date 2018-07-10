package com.chatapp.ipme.chatapp.ui.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.chatapp.ipme.chatapp.model.DisplayRoom;

import java.util.List;


public class RoomListViewModel extends ViewModel {

  private MutableLiveData<List<DisplayRoom>> rooms;

  public LiveData<List<DisplayRoom>> getRooms() {
    if (rooms == null) {
      rooms = new MutableLiveData<>();
      loadRooms();
    }
    return rooms;
  }

  private void loadRooms() {
  }
}
