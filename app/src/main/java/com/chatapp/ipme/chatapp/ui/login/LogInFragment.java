package com.chatapp.ipme.chatapp.ui.login;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chatapp.ipme.chatapp.HomeActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.deserializer.UserJsonDeserializer;
import com.chatapp.ipme.chatapp.model.User;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.ui.signUp.SignUpFragment;
import com.chatapp.ipme.chatapp.utils.AlertDialogManager;
import com.chatapp.ipme.chatapp.utils.ErrorManager;
import com.chatapp.ipme.chatapp.utils.SessionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.ConnectException;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static com.chatapp.ipme.chatapp.api.Constants.httpcodes.MESSAGE_CONNECT_EXCEPTION;

public class LogInFragment extends Fragment {

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    private HashMap<String, String> loginAccountMap = new HashMap<>();
    private AlertDialogManager alert = new AlertDialogManager();
    private ApiEndPointInterface apiInterface;
    private SessionManager session;
    private FrameLayout frameLayout;
    private ViewModel viewModel;
    private View rootView;
    private User user;

    // User variable declaration
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

        Button buttonLogIn = rootView.findViewById(R.id.buttonLog);
        TextView tvNoAccount = rootView.findViewById(R.id.tvNoAccount);

        inputLog = rootView.findViewById(R.id.inputLog);
        inputPassword = rootView.findViewById(R.id.inputPassword);

        buttonLogIn.setOnClickListener(view -> {
            connectAccount();
        });

        tvNoAccount.setOnClickListener(v -> {
            Fragment f = SignUpFragment.newInstance();
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                    .replace(R.id.login_frame_container, f)
                    .addToBackStack(null)
                    .commit();
        });

        return rootView;
    }

    private void connectAccount() {
        initLogin();
        apiInterface = new ApiClient(getContext())
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.loginUser(loginAccountMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorManager<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        handleResponse();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ConnectException) {
                            Toast.makeText(getContext(), MESSAGE_CONNECT_EXCEPTION, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void initLogin() {
        logInLog = inputLog.getText().toString();
        logInPassword = inputPassword.getText().toString();
        loginAccountMap.put("username", logInLog);
        loginAccountMap.put("password", logInPassword);
    }

    private void handleResponse() {
        //getToken from response
        String token = user.getToken();

        //get deserializer for handle user response body
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class, new UserJsonDeserializer())
                .create();


//        User u = gson.fromJson(, User.class);

        //store data and intent activity
        if (logInLog.trim().length() > 0 && logInPassword.trim().length() > 0) {
            session.createLoginSession(logInLog, logInPassword, token, null);
            Intent intent = new Intent(getContext(), HomeActivity.class);
            startActivity(intent);
        }
    }
}
