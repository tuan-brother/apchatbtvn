package com.example.appchatfb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appchatfb.Adapter.ChatFriendAdapter;
import com.example.appchatfb.databinding.ActivityChatBinding;
import com.example.appchatfb.model.ChatMessage;
import com.example.appchatfb.model.User;
import com.example.appchatfb.viewmodel.ActivityChatViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityChat extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ActivityChatViewModel viewModel;
    LinearLayoutManager manager;
    ChatFriendAdapter adapter;
    User user;
    ArrayList<ChatMessage> listchat;
    Bundle bundle;
    ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        setSupportActionBar(binding.tbChatfriend);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.tbChatfriend.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //getIntent du lieu
        bundle = getIntent().getBundleExtra("bundle");
        user= new User(bundle.getString("email"), null, bundle.getString("name"), bundle.getString("image"), null, null);
        binding.setUser(user);
        binding.btnSendmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edtMess.getText().toString().equals("")) {
                    Toast.makeText(ActivityChat.this, "Ban chua nhap tin nhan", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance()
                            .getReference("CSDL").child("Chat")
                            .child("meomeo").push()
                            .setValue(new ChatMessage(binding.edtMess.getText().toString(), "" + user.getAnh(), mAuth.getCurrentUser().getEmail(), user.getEmail()));

                    // Clear the input
                    binding.edtMess.setText("");
                }
            }
        });
        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter = new ChatFriendAdapter(binding.getRoot().getContext());
        binding.rcFmChat.setLayoutManager(manager);
        binding.rcFmChat.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel = new ViewModelProvider(this).get(ActivityChatViewModel.class);
        viewModel.dataChat(user.getEmail()).observe(this, new Observer<ArrayList<ChatMessage>>() {
            @Override
            public void onChanged(ArrayList<ChatMessage> chatMessages) {
                listchat = chatMessages;
                adapter.setAdapter(listchat, user.getAnh());
                binding.rcFmChat.smoothScrollToPosition(binding.rcFmChat.getAdapter().getItemCount());
            }
        });
    }

    public boolean isVisible()
    {
        LinearLayoutManager linearLayoutManager=(LinearLayoutManager)binding.rcFmChat.getLayoutManager();
        int isEndLayout=linearLayoutManager.findLastCompletelyVisibleItemPosition();
        int itemcout=binding.rcFmChat.getAdapter().getItemCount();
        return (isEndLayout>=itemcout);
    }
}
