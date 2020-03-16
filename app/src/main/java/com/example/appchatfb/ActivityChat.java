package com.example.appchatfb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.appchatfb.databinding.ActivityChatBinding;
import com.example.appchatfb.model.User;

public class ActivityChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChatBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_chat);
        setSupportActionBar(binding.tbChatfriend);
        Bundle bundle=getIntent().getBundleExtra("bundle");
        User user=new User(null,null,bundle.getString("name"),bundle.getString("image"),null,null);
        binding.setUser(user);
    }
}
