package com.example.appchatfb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;

import com.example.appchatfb.Adapter.ViewPagerAdapter;
import com.example.appchatfb.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    ViewPager vpChat;
    TabLayout tlChat;
    ViewPagerAdapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menusetting);
        setSupportActionBar(toolbar);
        vpChat=findViewById(R.id.vp_Chat);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        vpChat.setAdapter(adapter);
        tlChat=findViewById(R.id.tb_Chat);
        tlChat.setupWithViewPager(vpChat);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusetting,menu);
        return true;
    }
}
