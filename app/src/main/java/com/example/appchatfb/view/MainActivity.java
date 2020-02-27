package com.example.appchatfb.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.appchatfb.Adapter.ViewPagerAdapter;
import com.example.appchatfb.R;
import com.example.appchatfb.viewmodel.AccSettingViewModel;
import com.google.android.material.tabs.TabLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ViewPager vpChat;
    TabLayout tlChat;
    ViewPagerAdapter adapter;
    Toolbar toolbar;
    private FragmentManager fragmentManager;
    private AccSettingViewModel viewModel;
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
        fragmentManager = getSupportFragmentManager();
        viewModel = new ViewModelProvider(this).get(AccSettingViewModel.class);
        viewModel.setUserFromEmail("nguyendanvn123@gmail.com");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusetting,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap img = (Bitmap) extras.get("data");
            viewModel.setAvatar(img);
        }else if(requestCode==122 && resultCode == RESULT_OK){
            try {
                Uri uri = data.getData();
                InputStream stream = getContentResolver().openInputStream(uri);
                Bitmap img  = BitmapFactory.decodeStream(stream);
                viewModel.setAvatar(img);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.acc_setting:
                AccSettingFragment fragment = new AccSettingFragment();
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up,R.anim.slide_in_down,R.anim.slide_out_down,R.anim.slide_out_up)
                        .replace(R.id.container,fragment).addToBackStack(null).commit();
                break;
            case R.id.all_user:

                break;
            case R.id.log_out:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
