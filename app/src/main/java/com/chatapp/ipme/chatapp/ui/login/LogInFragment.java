package com.chatapp.ipme.chatapp.ui.login;

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
import android.widget.Toast;

import com.chatapp.ipme.chatapp.HomeActivity;
import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.UserResponse;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.ui.signUp.SignUpFragment;
import com.chatapp.ipme.chatapp.utils.AlertDialogManager;
import com.chatapp.ipme.chatapp.utils.ErrorManager;
import com.chatapp.ipme.chatapp.utils.SessionManager;

import java.net.ConnectException;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.chatapp.ipme.chatapp.api.Constants.httpcodes.MESSAGE_CONNECT_EXCEPTION;

public class LogInFragment extends Fragment {

  public static LogInFragment newInstance() {
    return new LogInFragment();
  }

  private HashMap<String, String> loginAccountMap = new HashMap<>();
  private AlertDialogManager alert = new AlertDialogManager();
  private ApiEndPointInterface apiInterface;
  private ViewModel viewModel;
  private View rootView;

  // User variable declaration
  private EditText inputLog;
  private EditText inputPassword;
  private String logInLog;
  private String logInPassword;

  private String token;
  private Integer id;
  private String username;
  private String firstname;
  private String lastname;
  private String email;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    viewModel = ViewModelProviders.of(this).get(LogInViewModel.class);
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
      .subscribe(new ErrorManager<Response<UserResponse>>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Response<UserResponse> userResponseResponse) {

          token = userResponseResponse.body().getToken();
          id = userResponseResponse.body().getUser().getID();
          username = userResponseResponse.body().getUser().getUsername();
          firstname = userResponseResponse.body().getUser().getFirstname();
          lastname = userResponseResponse.body().getUser().getLastname();
          email = userResponseResponse.body().getUser().getEmail();

          createSessionData();

          Intent intent = new Intent(getContext(), HomeActivity.class);
          startActivity(intent);

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
    logInLog = inputLog.getText().toString().trim();
    logInPassword = inputPassword.getText().toString().trim();
    loginAccountMap.put("username", logInLog);
    loginAccountMap.put("password", logInPassword);
  }

  private boolean createSessionData() {
    SessionManager sessionManager = new SessionManager(getActivity().getBaseContext());
    HashMap<String, Object> user = new HashMap<>();

    user.put(SessionManager.KEY_TOKEN, token);
    user.put(SessionManager.KEY_ID, id);
    user.put(SessionManager.KEY_USERNAME, username);
    user.put(SessionManager.KEY_FIRSTNAME, firstname);
    user.put(SessionManager.KEY_LASTNAME, lastname);
    user.put(SessionManager.KEY_EMAIL, email);

    return sessionManager.createLoginSession(user);
  }
}
