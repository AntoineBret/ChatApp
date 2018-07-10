package com.chatapp.ipme.chatapp.ui.room;

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

import com.chatapp.ipme.chatapp.ContactActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.DisplayRoom;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RoomListFragment extends Fragment {

    public static RoomListFragment newInstance() {
        return new RoomListFragment();
    }

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private RoomListAdapter adapter;
    private List<DisplayRoom> displayRoomList;
    private ApiEndPointInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room, container, false);

        RoomListViewModel model = ViewModelProviders.of(this).get(RoomListViewModel.class);
        model.getRooms().observe(this, rooms -> {
        });

        recyclerView = rootView.findViewById(R.id.room_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        displayRoomList = new ArrayList<>();
        adapter = new RoomListAdapter(getContext(), displayRoomList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        floatingActionButton = rootView.findViewById(R.id.contactsFab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ContactActivity.class);
            startActivity(intent);
        });

        initializeRoomListData();
        return rootView;
    }

    private void initializeRoomListData() {
        apiInterface = new ApiClient(getContext())
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.getRooms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DisplayRoom>>() {


                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DisplayRoom> displayRoomList) {
                        adapter.setData(displayRoomList);
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