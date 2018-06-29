package com.chatapp.ipme.chatapp.ui.contact;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Contact;
import com.chatapp.ipme.chatapp.model.Message;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.utils.ErrorManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ContactFragment extends android.support.v4.app.Fragment {



    public static ContactFragment newInstance() {
        return new ContactFragment();
    }
    private String username;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private List<Contact> contactList = new ArrayList<>();
    private ApiEndPointInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        ContactViewModel model = ViewModelProviders.of(this).get(ContactViewModel.class);
        model.getUsers().observe(this, users -> {
        });

        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        recyclerView = rootView.findViewById(R.id.contact_recyclerView);
        recyclerView.setHasFixedSize(true);

        initializeContact();

        adapter = new ContactAdapter(getContext(), contactList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setTitle("Contact");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void initializeContact() {
        //todo
        apiInterface = ApiClient
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.getContact(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorManager<Contact>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Contact value) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}