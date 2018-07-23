package com.chatapp.ipme.chatapp.ui.roomDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Message;
import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.utils.ErrorManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class RoomDetailsFragment extends Fragment {

    public static RoomDetailsFragment newInstance() {
        return new RoomDetailsFragment();
    }

    private RecyclerView recyclerView;
    private RoomDetailsAdapter adapter;
    private List<Room> roomList = new ArrayList<>();
    private ApiEndPointInterface apiInterface;
    private HashMap<String, String> messageMap = new HashMap<>();

    //getRoomDetailsData
    private Integer roomID;
    private String userName;
    private Integer userID;
    private String interlocutorName;
    private Integer interlocutorID;

    //Vue
    private EditText messageInput;
    private String messageBody;
    private Button buttonSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_roomdetails, container, false);

        getRoomDetailsData();

        messageInput = rootView.findViewById(R.id.edittext_messageInput);
        buttonSend = rootView.findViewById(R.id.button_MessageSend);

        recyclerView = rootView.findViewById(R.id.roomdetails_recyclerView);
        recyclerView.setHasFixedSize(true);

        roomList = new ArrayList<>();
        adapter = new RoomDetailsAdapter(getContext(), roomList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        receiveInterlocutorMessage();

        messageBody = messageInput.getText().toString();
        buttonSend.setOnClickListener(view -> {
            sendMessageToService();
        });

        return rootView;
    }

    private void receiveInterlocutorMessage() {
        //get message
        apiInterface = new ApiClient(getContext())
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.getMessages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorManager<Response<Message>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<Message> messageResponse) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void sendMessageToService() {
        //send message
        apiInterface = new ApiClient(getContext())
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.sendMessages(messageMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorManager<Response<Message>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<Message> messageResponse) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getRoomDetailsData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            roomID = bundle.getInt("room_id");
            userName = bundle.getString("user_name");
            userID = bundle.getInt("user_id");
            interlocutorName = bundle.getString("interlocutor_name");
            interlocutorID = bundle.getInt("interlocutor_id");
        }
    }
}