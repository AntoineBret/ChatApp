package com.chatapp.ipme.chatapp.ui.login;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Login;
import com.chatapp.ipme.chatapp.model.User;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.ui.room.RoomFragment;
import com.chatapp.ipme.chatapp.utils.AlertDialogManager;
import com.chatapp.ipme.chatapp.utils.SessionManager;

import java.net.ConnectException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.chatapp.ipme.chatapp.api.BaseUrl.BASE_URL;
import static com.chatapp.ipme.chatapp.utils.AnimationManager.frameTransition;


public class LogInFragment extends Fragment {

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    private AlertDialogManager alert = new AlertDialogManager();
    private SessionManager session;
    private FrameLayout frameLayout;
    private ViewModel viewModel;
    private View rootView;
    private AnimatorSet flip;

    private EditText inputLog;
    private EditText inputPassword;
    private String signInLog;
    private String signInPassword;
    private String logInLog;
    private String logInPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(LogInViewModel.class);
        session = new SessionManager(getContext());

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        flip = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.flip);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        frameLayout = rootView.findViewById(R.id.login_frame_container);

        frameTransition(frameLayout, flip);
        LayoutInflater.from(getContext()).inflate(R.layout.welcome_cardview, frameLayout, true);

        firstConnectionFrame();

        return rootView;
    }

    //First connection Accept Terms and Conditions
    private void firstConnectionFrame() {
        Button buttonTermServiceAgree = rootView.findViewById(R.id.buttonTermServiceAgree);
        buttonTermServiceAgree.setOnClickListener(view -> {
            loginUser();
        });
    }

    //Create account
    private void signInUser() {
        frameTransition(frameLayout, flip);
        LayoutInflater.from(getContext()).inflate(R.layout.signin_cardview, frameLayout, true);

        Button buttonCreate = rootView.findViewById(R.id.buttonCreate);
        TextView tvAlreadyAccount = rootView.findViewById(R.id.tvAlreadyAccount);
        EditText inputUsernameCreate = rootView.findViewById(R.id.inputUsernameCreate);
        EditText inputPasswordCreate = rootView.findViewById(R.id.inputPasswordCreate);

        signInLog = inputUsernameCreate.getText().toString();
        signInPassword = inputPasswordCreate.getText().toString();

        buttonCreate.setOnClickListener(view -> {
           // createAccount();
        });

        tvAlreadyAccount.setOnClickListener(v -> {
            loginUser();
        });
    }

    //Connect with existing account
    private void loginUser() {
        frameTransition(frameLayout, flip);
        LayoutInflater.from(getContext()).inflate(R.layout.login_cardview, frameLayout, true);

        Button buttonLogIn = rootView.findViewById(R.id.buttonLog);
        TextView tvNoAccount = rootView.findViewById(R.id.tvNoAccount);
        inputLog = rootView.findViewById(R.id.inputLog);
        inputPassword = rootView.findViewById(R.id.inputPassword);

        buttonLogIn.setOnClickListener(view -> {
            connectAccount();
        });

        tvNoAccount.setOnClickListener(v -> {
            signInUser();
        });
    }

    private void connectAccount() {
        logInLog = inputLog.getText().toString();
        logInPassword = inputPassword.getText().toString();
        ApiEndPointInterface apiInterface = ApiClient.getClient().create(ApiEndPointInterface.class);
        HashMap<String,String> map  = new HashMap<>();
        map.put("username",logInLog);
        map.put("password", logInPassword);
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

  /*  private void createAccount() {
        //todo : improve logic duplicate
        ApiEndPointInterface apiInterface = ApiClient.getClient().create(ApiEndPointInterface.class);
        apiInterface.loginUser(new User(signInLog, signInPassword))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Login>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Login user) {
                        if (signInLog.trim().length() > 0 && signInPassword.trim().length() > 0) {
                            if (signInLog.equals(signInLog) && signInPassword.equals(signInPassword)) {
                                session.createLoginSession(signInLog, signInPassword);
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
                        alert.showAlertDialog(getContext(), "java.net.ConnectException:", "Failed to connect to" + BASE_URL, false);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }/*/
}
