package com.chatapp.ipme.chatapp.ui.roomDetails;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Message;
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

import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.BAD_CREDENTIALS;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_OK;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_UNAUTHORIZED;

public class RoomDetailsFragment extends Fragment {

    public static RoomDetailsFragment newInstance() {
        return new RoomDetailsFragment();
    }

    private RecyclerView recyclerView;
    private RoomDetailsAdapter adapter;
    private List<Message> messageList = new ArrayList<>();
    private Message message;
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
    private FloatingActionButton buttonSend;
    private ImageButton action_attachment;
    private ImageButton action_photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_roomdetails, container, false);

        getRoomDetailsData();

        messageInput = rootView.findViewById(R.id.edittext_messageInput);
        action_attachment = rootView.findViewById(R.id.action_attachment);
        action_photo = rootView.findViewById(R.id.action_photo);
        buttonSend = rootView.findViewById(R.id.action_message_send);

        recyclerView = rootView.findViewById(R.id.roomdetails_recyclerView);
        recyclerView.setHasFixedSize(true);

        messageList = new ArrayList<>();
        adapter = new RoomDetailsAdapter(getContext(), messageList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        receiveInterlocutorMessage();

        action_attachment.setOnClickListener(view -> {
            //todo : join attachment to message
        });

        action_photo.setOnClickListener(view -> {
            //todo : take photo and join to message
        });

        buttonSend.setOnClickListener(view -> sendMessageToService());

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
                        if ((messageResponse.code() == STATUS_OK) && (messageResponse.body() != null)) {
                            adapter.setData(messageList);
                            //get response body & set data to messageList for adapter
                            messageList.add(message);
                        } else if (messageResponse.code() == STATUS_UNAUTHORIZED) {
                            Toast.makeText(getContext(), R.string.update_data_error, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void sendMessageToService() {
        //send message
        messageBody = messageInput.getText().toString();
        messageMap.put(messageBody, userName);

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
                        if ((messageResponse.code() == STATUS_OK) && (messageResponse.body() != null)) {
                            adapter.setData(messageList);
                            //get response body & set data to messageList for adapter
                            messageList.add(message);
                        } else if (messageResponse.code() == STATUS_UNAUTHORIZED) {
                            Toast.makeText(getContext(), R.string.update_data_error, Toast.LENGTH_LONG).show();
                        }
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