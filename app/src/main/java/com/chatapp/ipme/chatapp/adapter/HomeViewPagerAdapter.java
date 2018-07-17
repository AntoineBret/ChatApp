package com.chatapp.ipme.chatapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chatapp.ipme.chatapp.R;
import com.chatapp.ipme.chatapp.ui.room.RoomListFragment;
import com.chatapp.ipme.chatapp.ui.status.StatusFragment;

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public HomeViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RoomListFragment.newInstance();
            case 1:
                return StatusFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.room_fragment_title);
            case 1:
                return context.getString(R.string.status_fragment_title);
        }
        return null;
    }
}
