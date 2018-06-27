package com.chatapp.ipme.chatapp.ui.login;

import android.animation.AnimatorSet;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.HomeActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Login;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.ui.room.RoomFragment;
import com.chatapp.ipme.chatapp.ui.signIn.SignInFragment;
import com.chatapp.ipme.chatapp.utils.AlertDialogManager;
import com.chatapp.ipme.chatapp.utils.SessionManager;

import java.net.ConnectException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.chatapp.ipme.chatapp.api.BaseUrl.BASE_URL;

public class LogInFragment extends Fragment {

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    private AlertDialogManager alert = new AlertDialogManager();
    private ApiEndPointInterface apiInterface;
    private SessionManager session;
    private FrameLayout frameLayout;
    private ViewModel viewModel;
    private View rootView;
    private AnimatorSet flip;

    // Login variable declaration
    private EditText inputLog;
    private EditText inputPassword;
    private String logInLog;
    private String logInPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(LogInViewModel.class);
        session = new SessionManager(getContext());
    }

    //Connect with existing account
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);

        LayoutInflater.from(getContext()).inflate(R.layout.login_cardview, frameLayout, true);

        Button buttonLogIn = rootView.findViewById(R.id.buttonLog);
        TextView tvNoAccount = rootView.findViewById(R.id.tvNoAccount);

        inputLog = rootView.findViewById(R.id.inputLog);
        inputPassword = rootView.findViewById(R.id.inputPassword);

        buttonLogIn.setOnClickListener(view -> {
            connectAccount();
        });

        tvNoAccount.setOnClickListener(v -> {
            Fragment f = SignInFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.login_frame_container, f).addToBackStack(null).commit();
        });

        return rootView;
    }

    private void connectAccount() {
        logInLog = inputLog.getText().toString();
        logInPassword = inputPassword.getText().toString();

        HashMap<String, String> map = new HashMap<>();
        map.put("username", logInLog);
        map.put("password", logInPassword);

        apiInterface = ApiClient.getClient().create(ApiEndPointInterface.class);
        apiInterface.loginUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Login user) {
                        if (logInLog.trim().length() > 0 && logInPassword.trim().length() > 0) {
                            if (logInLog.equals(logInLog) && logInPassword.equals(logInPassword)) {
                                session.createLoginSession(logInLog, logInPassword);

                                Intent intent = new Intent (getContext(), HomeActivity.class);
                                Fragment f = RoomFragment.newInstance();
                                getFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
                            } else {
                                alert.showAlertDialog(getContext(), "Login failed..", "Username/Password is incorrect", false);
                            }
                        } else {
                            alert.showAlertDialog(getContext(), "Login failed..", "Please enter username and password", false);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof ConnectException) {
                            alert.showAlertDialog(getContext(), "java.net.ConnectException:", "Failed to connect to" + BASE_URL, false);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
