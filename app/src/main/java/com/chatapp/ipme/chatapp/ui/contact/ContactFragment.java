package com.chatapp.ipme.chatapp.ui.contact;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.User;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.session.Chatapp;
import com.chatapp.ipme.chatapp.ui.newContact.NewContactFragment;
import com.chatapp.ipme.chatapp.ui.newGroup.NewGroupFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_OK;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_UNAUTHORIZED;


public class ContactFragment extends android.support.v4.app.Fragment {

    public ContactFragment() {
    }

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private List<User> userList;
    private ApiEndPointInterface apiInterface;
    private RelativeLayout groupContainer;
    private RelativeLayout contactContainer;
    private Integer usernameLoggedID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        ContactViewModel model = ViewModelProviders.of(this).get(ContactViewModel.class);
        model.getUsers().observe(this, users -> {
        });

        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.contact_toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        recyclerView = rootView.findViewById(R.id.contact_recyclerView);
        recyclerView.setHasFixedSize(true);

        userList = new ArrayList<>();
        adapter = new ContactAdapter(getContext(), userList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        initializeContact();

        //Add new group
        groupContainer = rootView.findViewById(R.id.rl_container_group);
        groupContainer.setOnClickListener(view -> {
            Fragment newGroup = NewGroupFragment.newInstance();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contact_frame_container, newGroup)
                    .addToBackStack(null)
                    .commit();
        });

        //Add new createLoginSession
        contactContainer = rootView.findViewById(R.id.rl_container_contact);
        contactContainer.setOnClickListener(view -> {
            Fragment newContact = NewContactFragment.newInstance();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contact_frame_container, newContact)
                    .addToBackStack(null)
                    .commit();
        });

        return rootView;
    }

    private void initializeContact() {
        apiInterface = new ApiClient(getContext())
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.getContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<User>>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<List<User>> response) {
                        if ((response.code() == STATUS_OK)&& (response.body() != null)) {
                            adapter.setData(userList);
                            for (User user : response.body()) {
                                usernameLoggedID = Chatapp.getCurrentUserID();
                                if (!usernameLoggedID.equals(user.getID())) {
                                    user.setID(user.getID());
                                    userList.add(user);
                                }
                            }
                        } else if (response.code() == STATUS_UNAUTHORIZED) {
                            Toast.makeText(getContext(), R.string.display_data_error, Toast.LENGTH_LONG).show();
                        }

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
