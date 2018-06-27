package com.chatapp.ipme.chatapp.ui.signIn;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Signin;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.ui.login.LogInFragment;
import com.chatapp.ipme.chatapp.ui.login.LogInViewModel;
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

public class SignInFragment extends Fragment {

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    private HashMap<String, String> createAccountMAp = new HashMap<>();

    private AlertDialogManager alert = new AlertDialogManager();
    private ApiEndPointInterface apiInterface;
    private ViewModel viewModel;
    private SessionManager session;

    //onClickListener
    private Button buttonCreate;
    private TextView tvAlreadyAccount;

    //Variables
    private String signInLog;
    private String signInPassword;
    private String signInConfirmPassword;
    private String signInFirstName;
    private String signInLastName;
    private String signInBirthdayDate;

    private EditText inputUsernameCreate;
    private EditText inputPasswordCreate;
    private EditText inputConfirmPasswordCreate;
    private EditText inputFirstNameCreate;
    private EditText inputLastNameCreate;
    private EditText inputBirthdayDateCreate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        session = new SessionManager(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signin, container, false);

        buttonCreate = rootView.findViewById(R.id.buttonCreate);
        tvAlreadyAccount = rootView.findViewById(R.id.tvAlreadyAccount);

        inputUsernameCreate = rootView.findViewById(R.id.inputUsernameCreate);
        inputPasswordCreate = rootView.findViewById(R.id.inputPasswordCreate);
        inputConfirmPasswordCreate = rootView.findViewById(R.id.inputConfirmPasswordCreate);
        inputFirstNameCreate = rootView.findViewById(R.id.inputFirstNameCreate);
        inputLastNameCreate = rootView.findViewById(R.id.inputLastNameCreate);
        inputBirthdayDateCreate = rootView.findViewById(R.id.birthdayDateLastNameCreate);

        buttonCreate.setOnClickListener(view -> {
            createAccount();
        });

        tvAlreadyAccount.setOnClickListener(v -> {
            Fragment f = LogInFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.login_frame_container, f).addToBackStack(null).commit();
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signInLog = inputUsernameCreate.getText().toString();
        signInPassword = inputPasswordCreate.getText().toString();
        signInConfirmPassword = inputConfirmPasswordCreate.getText().toString();
        signInFirstName = inputFirstNameCreate.getText().toString();
        signInLastName = inputLastNameCreate.getText().toString();
        signInBirthdayDate = inputBirthdayDateCreate.getText().toString();

        createAccountMAp.put("username", signInLog);
        createAccountMAp.put("password", signInPassword);
        createAccountMAp.put("firstName", signInFirstName);
        createAccountMAp.put("lastName", signInLastName);
        createAccountMAp.put("birthdayDate", signInBirthdayDate);
    }

    private void createAccount() {
        apiInterface = ApiClient.getClient().create(ApiEndPointInterface.class);

        if (!(signInPassword.equals(signInConfirmPassword))) {
            alert.showAlertDialog(getContext(), "Password error", "Please enter same password", false);
        }
        if (signInPassword.length() < 8) {
            alert.showAlertDialog(getContext(), "Password error", "Your password must have more than 8 character", false);
        } else {
            apiInterface.signinUser(createAccountMAp)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Signin>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Signin value) {
                            Fragment f = RoomFragment.newInstance();
                            getFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof ConnectException) {
                                alert.showAlertDialog(getContext(), "java.net.ConnectException:", "Failed to connect to" + BASE_URL, false);
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
