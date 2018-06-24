package com.chatapp.ipme.chatapp.ui.logout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.utils.AlertDialogManager;
import com.chatapp.ipme.chatapp.utils.SessionManager;

import java.util.HashMap;

public class LogOutFragment extends Fragment {

  public static LogOutFragment newInstance() {
    return new LogOutFragment();
  }

  private AlertDialogManager alert = new AlertDialogManager();
  private SessionManager session;

  private TextView displayUsername;
  private Button buttonLogOut;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    session = new SessionManager(getContext());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_logout, container, false);

    displayUsername = rootView.findViewById(R.id.displayUsername);
    buttonLogOut = rootView.findViewById(R.id.buttonLogOut);

    return rootView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getUserLogDetails();

    buttonLogOut.setOnClickListener(arg0 -> session.logoutUser());
  }

  private void getUserLogDetails() {
    Toast.makeText(getContext(), "Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
    //checkLogin disconect automatically if no log effective
    /* session.checkLogin();*/

    HashMap<String, String> user = session.getUserDetails();

    String name = user.get(SessionManager.KEY_NAME);
    displayUsername.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
  }
}
