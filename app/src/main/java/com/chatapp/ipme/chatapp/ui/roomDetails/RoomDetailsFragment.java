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
import com.chatapp.ipme.chatapp.ui.bottomSheet.AttachmentFragment;
import com.chatapp.ipme.chatapp.utils.ErrorManager;
import com.flipboard.bottomsheet.BottomSheetLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_OK;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_UNAUTHORIZED;

public class RoomDetailsFragment extends Fragment {

    public static RoomDetailsFragment newInstance() {
        return new RoomDetailsFragment();
    }

    private RecyclerView recyclerView;
    private RoomDetailsAdapter adapter;
    private List<Message> messageList = new ArrayList<>();
    private ApiEndPointInterface apiInterface;
    private HashMap<String, String> messageMap = new HashMap<>();

    //getRoomDetailsData
    private Integer roomID;
    private String userName;
    private Integer userID;
    private String interlocutorName;
    private Integer interlocutorID;
    private String content;

    //Vue
    private EditText messageInput;
    private String messageBody;
    private FloatingActionButton button_attachment;
    private ImageButton button_send;

    protected BottomSheetLayout bottomSheetLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_roomdetails, container, false);

        getRoomDetailsData();

        //Vue
        messageInput = rootView.findViewById(R.id.edittext_messageInput);
        button_attachment = rootView.findViewById(R.id.action_attachment);
        button_send = rootView.findViewById(R.id.action_message_send);

        //bottom_sheet component
        bottomSheetLayout = rootView.findViewById(R.id.bottomsheet);
        bottomSheetLayout.setPeekOnDismiss(true);

        recyclerView = rootView.findViewById(R.id.roomdetails_recyclerView);
        recyclerView.setHasFixedSize(true);

        messageList = new ArrayList<>();
        adapter = new RoomDetailsAdapter(getContext(), messageList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        receiveInterlocutorMessage();

        button_send.setOnClickListener(view ->
                sendMessageToService());
        button_attachment.setOnClickListener(v ->
                new AttachmentFragment().show(getFragmentManager(), R.id.bottomsheet));
        return rootView;
    }

    private void receiveInterlocutorMessage() {
        //get message
        apiInterface = new ApiClient(getContext())
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.getRoomMessages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorManager<Response<List<Message>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<List<Message>> messageResponse) {
                        if ((messageResponse.code() == STATUS_OK) && (messageResponse.body() != null)) {
                            adapter.setData(messageList);
                            for (Message message : messageResponse.body()) {
                                content = message.getMessageContent();
                                message.setMessageContent(content);
                                messageList.add(message);
                            }
                        } else if (messageResponse.code() == STATUS_UNAUTHORIZED) {
                            Toast.makeText(getContext(), R.string.update_data_error, Toast.LENGTH_LONG).show();
                        }
                        //focus recyclerview on latest item
                        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
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
