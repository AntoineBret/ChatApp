package com.chatapp.ipme.chatapp.ui.signIn;

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
import android.widget.TextView;

import com.chatapp.ipme.chatapp.HomeActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Signin;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.ui.login.LogInFragment;
import com.chatapp.ipme.chatapp.utils.AlertDialogManager;
import com.chatapp.ipme.chatapp.utils.ErrorManager;
import com.chatapp.ipme.chatapp.utils.SessionManager;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignInFragment extends Fragment {

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    private HashMap<String, String> createAccountMap = new HashMap<>();

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

    private void createAccount() {
        initSigninForm();
        apiInterface = ApiClient
                .getClient()
                .create(ApiEndPointInterface.class);

        if (!(signInPassword.equals(signInConfirmPassword))) {
            alert.showAlertDialog(getContext(), "Password error", "Please enter same password", false);
        }
        if (signInLog.length() < 4) {
            alert.showAlertDialog(getContext(), "Username error", "Your username must have more than 4 character", false);
        }
        if (signInPassword.length() < 8) {
            alert.showAlertDialog(getContext(), "Password error", "Your password must have more than 8 character", false);
        } else {
            apiInterface.signinUser(createAccountMap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ErrorManager<Signin>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Signin value) {
                            String token = value.getToken();
                            if (signInLog.trim().length() > 0 && signInPassword.trim().length() > 0) {
                                session.createLoginSession(signInLog, signInPassword, token);
                                Intent intent = new Intent(getContext(), HomeActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    private void initSigninForm() {
        signInLog = inputUsernameCreate.getText().toString();
        signInPassword = inputPasswordCreate.getText().toString();
        signInConfirmPassword = inputConfirmPasswordCreate.getText().toString();
        signInFirstName = inputFirstNameCreate.getText().toString();
        signInLastName = inputLastNameCreate.getText().toString();
        signInBirthdayDate = inputBirthdayDateCreate.getText().toString();

        createAccountMap.put("username", signInLog);
        createAccountMap.put("password", signInPassword);
        createAccountMap.put("firstName", signInFirstName);
        createAccountMap.put("lastName", signInLastName);
        createAccountMap.put("birthdayDate", signInBirthdayDate);
    }
}
