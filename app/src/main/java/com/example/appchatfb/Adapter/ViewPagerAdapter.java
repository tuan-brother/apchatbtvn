package com.example.appchatfb.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appchatfb.view.FragmentChat;
import com.example.appchatfb.view.FragmentFriend;
import com.example.appchatfb.view.FragmentRequest;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if(position==0)
        {
            fragment=new FragmentRequest();
        }
        if(position==1)
        {
            fragment=new FragmentChat();
        }
        if (position==2)
        {
            fragment=new FragmentFriend();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0)
        {
            title="Requests";
        }
        if(position==1)
        {
            title="Chats";
        }
        if(position==2)
        {
            title="Friends";
        }
        return title;
    }
}
