package com.chatapp.ipme.chatapp.ui.status;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Status;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment {

    public static StatusFragment newInstance() {
        return new StatusFragment();
    }

    private RecyclerView recyclerView;
    private StatusAdapter adapter;
    private List<Status> statusList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_status, container, false);

//        StatusViewModel model = ViewModelProviders.of(this).get(StatusViewModel.class);
//        model.getStatus().observe(this, profile -> {
//        });

        recyclerView = rootView.findViewById(R.id.status_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        statusList = new ArrayList<>();
        adapter = new StatusAdapter(getContext(), statusList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        // initializeProfile();

        return rootView;
    }
}