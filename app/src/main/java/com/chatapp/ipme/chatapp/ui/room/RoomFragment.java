package com.chatapp.ipme.chatapp.ui.room;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.model.Room;
import com.chatapp.ipme.chatapp.ui.contact.ContactFragment;
import com.chatapp.ipme.chatapp.ui.logout.LogOutFragment;

import java.util.ArrayList;
import java.util.List;

public class RoomFragment extends Fragment {

  public static RoomFragment newInstance() {
    return new RoomFragment();
  }

  private Menu menu;
  private Toolbar toolbar;
  private FloatingActionButton floatingActionButton;
  private RecyclerView recyclerView;
  private RoomAdapter adapter;
  private List<Room> roomList;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_room, container, false);

    RoomViewModel model = ViewModelProviders.of(this).get(RoomViewModel.class);
    model.getRooms().observe(this, rooms -> {
    });

    toolbar = rootView.findViewById(R.id.toolbar);
    toolbar.setTitle("");
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

    floatingActionButton = rootView.findViewById(R.id.contactsFab);
    floatingActionButton.setOnClickListener(view -> {
      Fragment f = ContactFragment.newInstance();
      getFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
    });

    recyclerView = rootView.findViewById(R.id.room_recyclerView);
    recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    recyclerView.setHasFixedSize(true);

    roomList = new ArrayList<>();
    adapter = new RoomAdapter(getContext(), roomList);

    RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(manager);
    recyclerView.setAdapter(adapter);

    recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
      @Override
      public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
      }

      @Override
      public void onTouchEvent(RecyclerView rv, MotionEvent e) {

      }

      @Override
      public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

      }
    });

    initializeRoom();

    return rootView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    toolbar.setTitle("Discussion");
    toolbar.setTitleTextColor(getResources().getColor(R.color.white));
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_main, menu);
    this.menu = menu;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_option:
        Fragment f = LogOutFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void initializeRoom() {
    int[] covers = new int[]{
      R.drawable.test_thumbnail,
      R.drawable.test_thumbnail,};

    Room r = new Room("disc title one","disc subtitle one", covers[0]);
    roomList.add(r);
    r = new Room("disc title two","disc subtitle two", covers[1]);
    roomList.add(r);
  }
}
