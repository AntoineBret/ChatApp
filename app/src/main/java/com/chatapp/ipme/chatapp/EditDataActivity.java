package com.chatapp.ipme.chatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chatapp.ipme.chatapp.model.User;
import com.chatapp.ipme.chatapp.remote.ApiClient;
import com.chatapp.ipme.chatapp.remote.ApiEndPointInterface;
import com.chatapp.ipme.chatapp.session.Chatapp;
import com.chatapp.ipme.chatapp.session.SessionCreator;
import com.chatapp.ipme.chatapp.utils.AlertDialogManager;
import com.chatapp.ipme.chatapp.utils.ErrorManager;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class EditDataActivity extends AppCompatActivity implements SessionCreator {

    private Toolbar toolbar;
    private ApiEndPointInterface apiInterface;
    private HashMap<String, Object> editUserDataMap = new HashMap<>();
    private AlertDialogManager alert = new AlertDialogManager();

    private EditText newValueEdittext;
    private String item_to_edit;
    private String userDataNewValue;
    private Button button_ok;
    private Button button_cancel;

    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private String birthday;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        getDataFromExtras();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        newValueEdittext = findViewById(R.id.newValueEdittext);

        if (item_to_edit.equals("Username")) {
            toolbar.setTitle("Saisissez votre pseudo");
        } else if (item_to_edit.equals("Firstname")) {
            toolbar.setTitle("Saisissez votre prénom");
        } else if (item_to_edit.equals("Lastname")) {
            toolbar.setTitle("Saisissez votre nom");
        } else if (item_to_edit.equals("Email")) {
            toolbar.setTitle("Saisissez votre email");
        } else if (item_to_edit.equals("Birthday")) {
            toolbar.setTitle("Saisissez votre date de naissance");
        }

        button_ok = findViewById(R.id.button_ok);
        button_ok.setOnClickListener(view -> {
            if (item_to_edit.equals("Username")) {
                Toast.makeText(EditDataActivity.this, "Svp reconnectez vous après", Toast.LENGTH_LONG).show();
                initEditData();
            } else {
                //make hashmap before sending to backend
                initEditData();
            }
        });


        button_cancel = findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(view ->
                //return to previous view
                onBackPressed());
    }

    private void getDataFromExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            item_to_edit = extras.getString("profile_item");
        }
    }

    private void initEditData() {
        userDataNewValue = newValueEdittext.getText().toString();
        //backend need id + username + password for authorize data edit ><
        // todo : change backend logic
        editUserDataMap.put("id", Chatapp.getCurrentUserID());
        editUserDataMap.put("username", Chatapp.getCurrentUserName());
        editUserDataMap.put("password", Chatapp.getCurrentPassword());
        //Send new data for change to backend
        editUserDataMap.put((item_to_edit.toLowerCase()), userDataNewValue);
        sendNewUserData();
    }

    private void sendNewUserData() {
        apiInterface = new ApiClient(getApplicationContext())
                .getClient()
                .create(ApiEndPointInterface.class);

        apiInterface.editUser(editUserDataMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorManager<Response<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Response<User> userResponseResponse) {

                        username = userResponseResponse.body().getUsername();
                        firstname = userResponseResponse.body().getFirstname();
                        lastname = userResponseResponse.body().getLastname();
                        birthday = userResponseResponse.body().getBirthday();
                        email = userResponseResponse.body().getEmail();

                        editSessionData();

                        onBackPressed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void editSessionData() {
        if (item_to_edit.equals("Username")) {
            editUsername(username);
        } else if (item_to_edit.equals("Firstname")) {
            editFirstname(firstname);
        } else if (item_to_edit.equals("Lastname")) {
            editLastname(lastname);
        } else if (item_to_edit.equals("Email")) {
            editEmail(email);
        } else if (item_to_edit.equals("Birthday")) {
            editBirthday(birthday);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}