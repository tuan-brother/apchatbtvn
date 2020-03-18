package com.example.appchatfb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.appchatfb.Adapter.ChatFriendAdapter;
import com.example.appchatfb.databinding.ActivityChatBinding;
import com.example.appchatfb.model.ChatMessage;
import com.example.appchatfb.model.User;
import com.example.appchatfb.view.AccSettingFragment;
import com.example.appchatfb.viewmodel.AccSettingViewModel;
import com.example.appchatfb.viewmodel.ActivityChatViewModel;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityChat extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ActivityChatViewModel viewModel;
    DatabaseReference mRef;
    LinearLayoutManager manager;
    ChatFriendAdapter adapter;
    ArrayList<ChatMessage> listchat;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChatBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        setSupportActionBar(binding.tbChatfriend);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.tbChatfriend.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bundle = getIntent().getBundleExtra("bundle");
        User user = new User(bundle.getString("email"), null, bundle.getString("name"), bundle.getString("image"), null, null);
        binding.setUser(user);
        binding.btnSendmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance()
                        .getReference("CSDL/Chat")
                        .child("meomeo").push()
                        .setValue(new ChatMessage(binding.edtMess.getText().toString(),"", mAuth.getCurrentUser().getEmail(), user.getEmail()));

                // Clear the input
                binding.edtMess.setText("");
            }
        });
        manager=new LinearLayoutManager(this);
        adapter=new ChatFriendAdapter(binding.getRoot().getContext());
        binding.rcFmChat.setLayoutManager(manager);
        viewModel=new ViewModelProvider(this).get(ActivityChatViewModel.class);
        viewModel.dataChat(user.getEmail()).observe(this, new Observer<ArrayList<ChatMessage>>() {
            @Override
            public void onChanged(ArrayList<ChatMessage> chatMessages) {
                listchat=chatMessages;
                adapter.setAdapter(listchat);
            }
        });

        //AdapterChat("");
        binding.rcFmChat.setAdapter(adapter);
    }

//    public void AdapterChat(String urlanh) {
//        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
//                R.layout.message, FirebaseDatabase.getInstance().getReference("CSDL/Chat/meomeo")) {
//            @Override
//            protected void populateView(View v, ChatMessage model, int position) {
//                if (model.getReciver().equals(mAuth.getCurrentUser().getEmail()) && model.getSender().equals(bundle.getString("email")) ||
//                        model.getReciver().equals(bundle.getString("email")) && model.getSender().equals(mAuth.getCurrentUser().getEmail())) {
//                    TextView messageText = (TextView) v.findViewById(R.id.message_text);
//                    ImageView ImageUser = (ImageView) v.findViewById(R.id.image_chat);
//                    TextView messageTime = (TextView) v.findViewById(R.id.message_time);
//
//                    // Set their text
//                    messageText.setText(model.getMessageText());
//                    //messageUser.setText(model.getMessageUser());
//                    Glide.with(getApplicationContext()).load(urlanh)
//                            .centerCrop().into(ImageUser);
//                    // Format the date before showing it
//                    messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
//                            model.getMessageTime()));
//                }
//            }
//        };
//    }
}
