package com.chatapp.ipme.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.session.SessionKeys;
import com.chatapp.ipme.chatapp.session.SessionManager;
import com.chatapp.ipme.chatapp.ui.roomDetails.RoomDetailsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RoomActivity extends AppCompatActivity {

  private HashMap<String, Object> createUsersMap = new HashMap<>();
  private SessionManager session;
  private FrameLayout frameLayout;
  private ApiEndPointInterface apiInterface;
  private Toolbar toolbar;
  private String interlocutorName;
  private int interlocutorID;
  private int userID;
  private List<Map<String, Integer>> postUsersID;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_room);

    new SessionManager.Builder()
      .setContext(getApplicationContext())
      .setPrefsName(SessionKeys.PREFS_NAME.getKey())
      .build();

    frameLayout = findViewById(R.id.room_frame_container);

    initializeDataBeforeCreateRoom();

    toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle(interlocutorName);
    toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    setSupportActionBar(toolbar);

    createRoom();
  }

  private void initializeDataBeforeCreateRoom() {
    //get interlocutor name + id from previous ContactAdapter to RoomActivity
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      interlocutorName = extras.getString("user_name"); // = Room name
      interlocutorID = extras.getInt("user_id");
    }

    //get ID of user currently log with session manager
    userID = SessionManager.getInt(SessionKeys.KEY_ID.getKey(), 0);
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
      .subscribe(new Observer<Room>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Room room) {
          //todo send room ID after created, with Bundle argument to roomDetails
          Integer roomID = room.getId();

          Bundle bundle = new Bundle();
          bundle.putInt("room_id", roomID);
          Fragment roomDetails = RoomDetailsFragment.newInstance();
          roomDetails.setArguments(bundle);

          getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.room_frame_container, roomDetails)
            .disallowAddToBackStack()
            .commit();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
      });
  }

  @Override
  public void onBackPressed() {
    finish();
    Intent intent = new Intent(this, HomeActivity.class);
    startActivity(intent);
    super.onBackPressed();
  }
}
