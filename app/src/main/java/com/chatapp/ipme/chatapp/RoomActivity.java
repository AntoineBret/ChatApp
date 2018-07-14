package com.chatapp.ipme.chatapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.chatapp.ipme.chatapp.model.Room;
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
    private String interlocutorID;

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
//            interlocutorID = extras.getString("user_id");
        }

        //get data of user currently log


        //get ID of user currently connected
//        if (SessionManager.KEY_ID != null) {
//            Map<String, String> user = session.getUserDetails();
//            String id = user.get(SessionManager.KEY_ID);
//        }else{
//            //todo récupérer l'ID avec une methode get
//        }
    }

    private void createRoom() {
        //todo : check if " " value of hashmap are correct for the api call
        //room name
        createRoomMap.put("name", interlocutorUsername);
        //interlocutor name
        createRoomMap.put("users1", interlocutorUsername);
        //currently logged user
//        createRoomMap.put("users2",interlocutorID);

        apiInterface = new ApiClient(this)
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.createNewRoom(createRoomMap)
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
