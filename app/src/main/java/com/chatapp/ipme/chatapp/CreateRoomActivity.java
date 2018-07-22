package com.chatapp.ipme.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.session.SessionKeys;
import com.chatapp.ipme.chatapp.session.SessionManager;
import com.chatapp.ipme.chatapp.utils.ErrorManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateRoomActivity extends AppCompatActivity {

  private HashMap<String, Object> createUsersMap = new HashMap<>();
  private SessionManager session;
  private FrameLayout frameLayout;
  private ApiEndPointInterface apiInterface;
  private String interlocutorName;
  private int interlocutorID;
  private int userID;
  private List<Map<String, Integer>> postUsersID;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_room);

    new SessionManager.Builder()
      .setContext(getApplicationContext())
      .setPrefsName(SessionKeys.PREFS_NAME.getKey())
      .build();

    frameLayout = findViewById(R.id.room_frame_container);

    getExistingDataBeforeCreateRoom();
    handleDataBeforeApiCall();
    createRoom();
  }

  private void getExistingDataBeforeCreateRoom() {
    userID = SessionManager.getInt(SessionKeys.KEY_ID.getKey(), 0);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      interlocutorName = extras.getString("user_name");
      interlocutorID = extras.getInt("user_id");
    }
  }

  private void handleDataBeforeApiCall() {
    //aggregation of UserId + InterlocutorId
    postUsersID = new ArrayList<>();
    for (int i = 0; i < 2/* sizeMax*/; i++) {
      postUsersID.add(new HashMap<>());
    }
    postUsersID.get(0).put("id", userID);
    postUsersID.get(1).put("id", interlocutorID);

    //Map for subdata "users"
    createUsersMap.put("name", interlocutorName);
    createUsersMap.put("sizeMax", 2);
    createUsersMap.put("users", postUsersID);
  }

  private void createRoom() {
    apiInterface = new ApiClient(this)
      .getClient()
      .create(ApiEndPointInterface.class);

    apiInterface.createNewRoom(createUsersMap)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new ErrorManager<Room>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Room room) {
          Integer roomID = room.getId();
          Intent chatServiceIntent = new Intent(getApplicationContext(), ChatServiceActivity.class);
          chatServiceIntent.putExtra("room_id", roomID);
          chatServiceIntent.putExtra("interlocutor_name", interlocutorName);
          chatServiceIntent.putExtra("interlocutor_id", interlocutorID);
          startActivity(chatServiceIntent);
        }

        @Override
        public void onComplete() {
        }
      });
  }
}
