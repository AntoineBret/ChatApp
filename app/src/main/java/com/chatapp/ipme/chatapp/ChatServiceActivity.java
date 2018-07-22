package com.chatapp.ipme.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.adapter.ChatServiceAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatServiceActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private RecyclerView recyclerView;
  private ChatServiceAdapter adapter;
  private List<Room> roomList = new ArrayList<>();

  private int roomID;
  private String interlocutorName;
  private int interlocutorID;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat_service);

    getDataFromExtras();

    toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle(interlocutorName);
    toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    setSupportActionBar(toolbar);

    recyclerView = findViewById(R.id.chat_service_recyclerView);
    recyclerView.setHasFixedSize(true);

    roomList = new ArrayList<>();
    adapter = new ChatServiceAdapter(getApplicationContext(), roomList);

    RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(manager);
    recyclerView.setAdapter(adapter);

  }

  private void getDataFromExtras() {
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      roomID = extras.getInt("room_id"); // = Room name
      interlocutorName = extras.getString("interlocutor_name");
      interlocutorID = extras.getInt("interlocutor_id");
    }
  }

  @Override
  public void onBackPressed() {
    finish();
    Intent intent = new Intent(this, HomeActivity.class);
    startActivity(intent);
    super.onBackPressed();
  }
}
