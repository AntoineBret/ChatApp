package com.chatapp.ipme.chatapp.ui.roomList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chatapp.ipme.chatapp.ContactActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.model.User;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.session.Chatapp;
import com.chatapp.ipme.chatapp.utils.ErrorManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_OK;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_UNAUTHORIZED;

public class RoomListFragment extends Fragment {

    public static RoomListFragment newInstance() {
        return new RoomListFragment();
    }

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private RoomListAdapter adapter;
    private List<Room> roomList;
    private Room room;
    private ApiEndPointInterface apiInterface;
    private String usernameLogged;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room_list, container, false);

        RoomListViewModel model = ViewModelProviders.of(this).get(RoomListViewModel.class);
        model.getRooms().observe(this, rooms -> {
        });

        recyclerView = rootView.findViewById(R.id.room_list_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        roomList = new ArrayList<>();
        adapter = new RoomListAdapter(getContext(), roomList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        initializeRoomListData();

        floatingActionButton = rootView.findViewById(R.id.contactsFab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ContactActivity.class);
            startActivity(intent);
        });

        return rootView;
    }

    private void initializeRoomListData() {
        apiInterface = new ApiClient(getContext())
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.getRooms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorManager<Response<List<Room>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Response<List<Room>> response) {
                        if ((response.code() == STATUS_OK) && (response.body() != null)) {
                            adapter.setData(roomList);

                            for (Room room : response.body()) {
                                for (User user : (List<User>) room.getUsers()) {
                                    usernameLogged = Chatapp.getCurrentUserName();
                                    if (!usernameLogged.equals(user.getUsername())) {
                                        room.setName(user.getUsername());
                                        roomList.add(room);
                                    }
                                }
                            }
                        } else if (response.code() == STATUS_UNAUTHORIZED) {
                            Toast.makeText(getContext(), R.string.update_data_error, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
