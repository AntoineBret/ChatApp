package com.chatapp.ipme.chatapp.ui.login;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Login;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.ui.contact.ContactFragment;
import com.chatapp.ipme.chatapp.utils.AlertDialogManager;
import com.chatapp.ipme.chatapp.utils.SessionManager;

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
  private SessionManager session;

  private EditText inputLog;
  private EditText inputPassword;
  private Button buttonLogIn;
  private static String log;
  private static String password;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_login, container, false);

    ViewModel viewModel = ViewModelProviders.of(this).get(LogInViewModel.class);
    session = new SessionManager(getContext());

    inputLog = rootView.findViewById(R.id.inputLog);
    inputPassword = rootView.findViewById(R.id.inputPassword);
    Toast.makeText(getContext(), "Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

    buttonLogIn = rootView.findViewById(R.id.buttonLog);
    buttonLogIn.setOnClickListener(view -> {
      loginUser();
    });
    return rootView;
  }

  //todo
  private void loginUser() {
    log = inputLog.getText().toString();
    password = inputPassword.getText().toString();

    ApiEndPointInterface apiInterface = ApiClient.getClient().create(ApiEndPointInterface.class);

    apiInterface.createUser(log, password)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Observer<Login>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Login user) {
          if (log.trim().length() > 0 && password.trim().length() > 0) {
            if (log.equals(log) && password.equals(password)) {
              session.createLoginSession(log, password);
              Fragment f = ContactFragment.newInstance();
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
          alert.showAlertDialog(getContext(), "java.net.ConnectException:", "Failed to connect to"+BASE_URL, false);
        }

        @Override
        public void onComplete() {
        }
      });
  }
}
