package com.chatapp.ipme.chatapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.chatapp.ipme.chatapp.adapter.HomeViewPagerAdapter;
import com.chatapp.ipme.chatapp.ui.logout.LogOutFragment;

public class HomeActivity extends AppCompatActivity {

    private Menu menu;
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private HomeViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.home_tablayout);
        viewPager = findViewById(R.id.home_viewpager);

        frameLayout = findViewById(R.id.home_frame_container);

        adapter = new HomeViewPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_option:
                Fragment f = LogOutFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.home_frame_container, f)
                        .addToBackStack(null).commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

