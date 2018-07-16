package com.chatapp.ipme.chatapp.ui.signUp;

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
import com.chatapp.ipme.chatapp.session.SessionCreator;
import com.chatapp.ipme.chatapp.session.SessionKeys;
import com.chatapp.ipme.chatapp.session.SessionManager;
import com.chatapp.ipme.chatapp.ui.login.LogInFragment;
import com.chatapp.ipme.chatapp.utils.AlertDialogManager;
import com.chatapp.ipme.chatapp.utils.ErrorManager;

import java.net.ConnectException;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.chatapp.ipme.chatapp.utils.Constants.httpcodes.MESSAGE_CONNECT_EXCEPTION;

public class SignUpFragment extends Fragment implements SessionCreator {

  public static SignUpFragment newInstance() {
    return new SignUpFragment();
  }

  private HashMap<String, String> createAccountMap = new HashMap<>();

  private AlertDialogManager alert = new AlertDialogManager();
  private ApiEndPointInterface apiInterface;
  private ViewModel viewModel;

  //onClickListener
  private Button buttonCreate;
  private TextView tvAlreadyAccount;

  //Variables
  private String token;
  private Integer id;
  private String username;
  private String firstname;
  private String lastname;
  private String email;

  private String signUpLog;
  private String signUpPassword;
  private String signUpConfirmPassword;
  private String signUpFirstName;
  private String signUpLastName;
  private String signUpBirthdayDate;

  private EditText inputUsernameCreate;
  private EditText inputPasswordCreate;
  private EditText inputConfirmPasswordCreate;
  private EditText inputFirstNameCreate;
  private EditText inputLastNameCreate;
  private EditText inputBirthdayDateCreate;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    viewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

    new SessionManager.Builder()
      .setContext(getContext())
      .setPrefsName(SessionKeys.PREFS_NAME.getKey())
      .build();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_signup, container, false);

    buttonCreate = rootView.findViewById(R.id.buttonCreate);
    tvAlreadyAccount = rootView.findViewById(R.id.tvAlreadyAccount);

    inputUsernameCreate = rootView.findViewById(R.id.inputUsernameCreate);
    inputPasswordCreate = rootView.findViewById(R.id.inputPasswordCreate);
    inputConfirmPasswordCreate = rootView.findViewById(R.id.inputConfirmPasswordCreate);
    inputFirstNameCreate = rootView.findViewById(R.id.inputFirstNameCreate);
    inputLastNameCreate = rootView.findViewById(R.id.inputLastNameCreate);
    inputBirthdayDateCreate = rootView.findViewById(R.id.birthdayDateLastNameCreate);

    buttonCreate.setOnClickListener(view -> createAccount());
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
    initSignupForm();
    apiInterface = new ApiClient(getContext())
      .getClient()
      .create(ApiEndPointInterface.class);

    if (!(signUpPassword.equals(signUpConfirmPassword))) {
      alert.showAlertDialog(getContext(), "Password error", "Please enter same password", false);
    }
    if (signUpLog.length() < 4) {
      alert.showAlertDialog(getContext(), "Username error", "Your username must have more than 4 character", false);
    }
    if (signUpPassword.length() < 8) {
      alert.showAlertDialog(getContext(), "Password error", "Your password must have more than 8 character", false);
    } else {
      apiInterface.signupUser(createAccountMap)
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

            createSessionData(token, id, username, firstname, lastname, email);

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
  }

  private void initSignupForm() {
    signUpLog = inputUsernameCreate.getText().toString();
    signUpPassword = inputPasswordCreate.getText().toString();
    signUpConfirmPassword = inputConfirmPasswordCreate.getText().toString();
    signUpFirstName = inputFirstNameCreate.getText().toString();
    signUpLastName = inputLastNameCreate.getText().toString();
    signUpBirthdayDate = inputBirthdayDateCreate.getText().toString();

    createAccountMap.put("username", signUpLog);
    createAccountMap.put("password", signUpPassword);
    createAccountMap.put("firstName", signUpFirstName);
    createAccountMap.put("lastName", signUpLastName);
    createAccountMap.put("birthdayDate", signUpBirthdayDate);
  }
}
