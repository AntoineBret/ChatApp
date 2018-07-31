package com.chatapp.ipme.chatapp.ui.profile;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chatapp.ipme.chatapp.ParameterActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Profile;
import com.chatapp.ipme.chatapp.model.User;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.session.Chatapp;
import com.chatapp.ipme.chatapp.session.SessionCreator;
import com.chatapp.ipme.chatapp.session.SessionHashMap;
import com.chatapp.ipme.chatapp.utils.ErrorManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import retrofit2.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_OK;
import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.STATUS_UNAUTHORIZED;

public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    private RecyclerView recyclerView;
    private ImageView user_thumbnail;
    private ProfileAdapter adapter;
    private List<Profile> profileList;
    private static Context context;

    //Current
    private String current_username = Chatapp.getCurrentUserName();
    private String current_firstname = Chatapp.getCurrentUserFirstname();
    private String current_lastname = Chatapp.getCurrentUserLastname();
    private String current_email = Chatapp.getCurrentUserEmail();
    private String current_birthday = Chatapp.getCurrentUserBirthday();
    private Integer current_thumbnail = Chatapp.getCurrentUserThumbnail();

    //Updated
    private String updated_username;
    private String updated_firstname;
    private String updated_lastname;
    private String updated_birthday;
    private String updated_email;
    private Integer updated_thumbnail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        ProfileViewModel model = ViewModelProviders.of(this).get(ProfileViewModel.class);
        model.getProfile().observe(this, profile -> {
        });

        ((ParameterActivity) getActivity()).getSupportActionBar().setTitle(R.string.profile_toolbar);

        user_thumbnail = rootView.findViewById(R.id.user_thumbnail);
        Glide
                .with(this)
                .load(getResources().getDrawable(R.drawable.test))
                .apply(new RequestOptions().circleCrop())
                .into(user_thumbnail);

        recyclerView = rootView.findViewById(R.id.profile_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        profileList = new ArrayList<>();
        adapter = new ProfileAdapter(getContext(), profileList, this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        ProfileFragment.context = getContext();

        initializeProfile();

        return rootView;
    }

    private void initializeProfile() {
        profileList.add(new Profile("Username", current_username));
        profileList.add(new Profile("Firstname", current_firstname));
        profileList.add(new Profile("Lastname", current_lastname));
        profileList.add(new Profile("Email", current_email));
        profileList.add(new Profile("Birthday", current_birthday));

        adapter.setData(profileList);
    }

    public void sendNewUserData() {
        ApiEndPointInterface apiInterface = new ApiClient(context)
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.editUser(SessionHashMap.getNewUserDataMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorManager<Response<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Response<User> userResponseResponse) {

                        if ((userResponseResponse.code() == STATUS_OK) && (userResponseResponse.body() != null)) {
                            updated_username = userResponseResponse.body().getUsername();
                            updated_firstname = userResponseResponse.body().getFirstname();
                            updated_lastname = userResponseResponse.body().getLastname();
                            updated_birthday = userResponseResponse.body().getBirthday();
                            updated_email = userResponseResponse.body().getEmail();
                            Toast.makeText(context, R.string.update_data_succes, Toast.LENGTH_LONG).show();
                            checkAndUpdateData();
                        } else if (userResponseResponse.code() == STATUS_UNAUTHORIZED) {
                            Toast.makeText(context, R.string.update_data_error, Toast.LENGTH_LONG).show();
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

    private void checkAndUpdateData() {
        if (!updated_username.equals(current_username)) {
            SessionCreator.editUsername(updated_username);
        } else if (!updated_firstname.equals(current_firstname)) {
            SessionCreator.editFirstname(updated_firstname);
        } else if (!updated_lastname.equals(current_lastname)) {
            SessionCreator.editFirstname(updated_lastname);
        } else if (!updated_email.equals(current_email)) {
            SessionCreator.editFirstname(updated_email);
        } else if (!updated_birthday.equals(current_birthday)) {
            SessionCreator.editFirstname(updated_birthday);
        }
    }
}
