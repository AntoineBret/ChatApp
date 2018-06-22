package com.chatapp.ipme.chatapp.ui.login;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.User;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndpointInterface;
import com.chatapp.ipme.chatapp.ui.contact.ContactFragment;
import com.chatapp.ipme.chatapp.ui.contact.ContactViewModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class LogInFragment extends Fragment {

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    private Toolbar toolbar;
    private TextView tvLogDescribe;
    private TextView tvPasswordDescribe;
    private EditText inputLog;
    private EditText inputPassword;
    private Button buttonLog;
    private static String log;
    private static String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        ViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        tvLogDescribe = rootView.findViewById(R.id.tvLogDescribe);
        tvPasswordDescribe = rootView.findViewById(R.id.tvPasswordDescribe);
        inputLog = rootView.findViewById(R.id.inputLog);
        inputPassword = rootView.findViewById(R.id.inputPassword);
        buttonLog = rootView.findViewById(R.id.buttonLog);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setTitle("Login");
        log = inputLog.getText().toString();
        password = inputPassword.getText().toString();

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        ApiEndpointInterface apiInterface = ApiClient.getClient().create(ApiEndpointInterface.class);
        apiInterface.createUser(log, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        Fragment f = ContactFragment.newInstance();
                        getFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}