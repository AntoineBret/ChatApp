package com.chatapp.ipme.chatapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.chatapp.ipme.chatapp.model.CreateRoom;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.ui.roomDetails.RoomDetailsFragment;
import com.chatapp.ipme.chatapp.utils.SessionManager;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RoomActivity extends AppCompatActivity {

    private HashMap<String, String> createRoomMap = new HashMap<>();
    private SessionManager session;
    private FrameLayout frameLayout;
    private ApiEndPointInterface apiInterface;
    private Toolbar toolbar;
    private String interlocutorUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        frameLayout = findViewById(R.id.room_frame_container);

        initializeDataBeforeCreateRoom();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(interlocutorUsername);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        createRoom();
    }

    private void initializeDataBeforeCreateRoom() {
        //get interlocutor data

        //get interlocutor username
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            interlocutorUsername = extras.getString("user_name");
        }

        //get interlocutor ID
        //todo

        //get data of user currently log


        //get ID of user currently connected

    }

    private void createRoom() {
        //room name
        createRoomMap.put("name", interlocutorUsername);
        //interlocutor name
        createRoomMap.put("users", interlocutorUsername);
        //currently logged user
        createRoomMap.put("user_name", interlocutorUsername);

        apiInterface = new ApiClient(this)
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.createNewRoom(createRoomMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreateRoom>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CreateRoom createRoom) {
                        Fragment roomDetails = RoomDetailsFragment.newInstance();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contact_frame_container, roomDetails)
                                .addToBackStack(null)
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
}