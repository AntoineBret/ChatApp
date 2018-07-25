package com.chatapp.ipme.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.session.Chatapp;
import com.chatapp.ipme.chatapp.session.SessionKeys;
import com.chatapp.ipme.chatapp.session.SessionManager;
import com.chatapp.ipme.chatapp.ui.roomDetails.RoomDetailsFragment;
import com.chatapp.ipme.chatapp.utils.ErrorManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.chatapp.ipme.chatapp.utils.Constants.INDIVIDUAL_ROOM_SIZE;

public class HandleRoomActivity extends AppCompatActivity {

    private HashMap<String, Object> createUsersMap = new HashMap<>();
    private FrameLayout frameLayout;
    private ApiEndPointInterface apiInterface;
    private Toolbar toolbar;

    private Integer roomID;
    private String userName;
    private Integer userID;
    private String interlocutorName;
    private Integer interlocutorID;

    private List<Map<String, Integer>> postUsersID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_room);

        getDataFromExtras();

        frameLayout = findViewById(R.id.handle_room_frame_container);
        toolbar = findViewById(R.id.toolbar);

        if (roomID != 0) {
            displayRoom();
        } else {
            createRoom();
        }

        toolbar.setTitle(interlocutorName);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void getDataFromExtras() {
        //From ContactAdapter if room doesn't exist, or RoomListAdapter if room exist
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            roomID = extras.getInt("room_id");
            userName = Chatapp.getCurrentUserName();
            userID = Chatapp.getCurrentUserID();
            interlocutorName = extras.getString("interlocutor_name");
            interlocutorID = extras.getInt("interlocutor_id");
        }
    }

    private void createRoom() {
        handleDataBeforeApiCall();
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
                        roomID = room.getId();
                        displayRoom();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void handleDataBeforeApiCall() {
        //aggregation of UserId + InterlocutorId
        postUsersID = new ArrayList<>();
        for (int i = 0; i < INDIVIDUAL_ROOM_SIZE; i++) {
            postUsersID.add(new HashMap<>());
        }
        postUsersID.get(0).put("id", userID);
        postUsersID.get(1).put("id", interlocutorID);

        //Map for subdata "users"
        createUsersMap.put("name", interlocutorName);
        createUsersMap.put("sizeMax", 2);
        createUsersMap.put("users", postUsersID);
    }

    private void displayRoom() {

        Bundle bundle = new Bundle();
        bundle.putInt("room_id", roomID);
        bundle.putString("user_name", userName);
        bundle.putInt("user_id", userID);
        bundle.putString("interlocutor_name", interlocutorName);
        bundle.putInt("interlocutor_id", interlocutorID);

        Fragment roomDetails = RoomDetailsFragment.newInstance();
        roomDetails.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.handle_room_frame_container, roomDetails)
                .disallowAddToBackStack()
                .commit();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
