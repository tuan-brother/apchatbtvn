package com.example.appchatfb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.appchatfb.Adapter.ChatFriendAdapter;
import com.example.appchatfb.Notification.Client;
import com.example.appchatfb.Notification.Token;
import com.example.appchatfb.base.BaseActivity;
import com.example.appchatfb.databinding.ActivityChatBinding;
import com.example.appchatfb.interfacefunc.APISerVice;
import com.example.appchatfb.model.ChatMessage;
import com.example.appchatfb.model.User;
import com.example.appchatfb.viewmodel.ActivityChatViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityChat extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ActivityChatViewModel viewModel;
    LinearLayoutManager manager;
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    ChatFriendAdapter adapter;
    User user;
    ArrayList<ChatMessage> listchat;
    Bundle bundle;
    ActivityChatBinding binding;
    APISerVice apiSerVice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        setSupportActionBar(binding.tbChatfriend);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        apiSerVice = Client.getClient(" https://fcm.googleapis.com/").create(APISerVice.class);
        binding.tbChatfriend.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //getIntent du lieu
        bundle = getIntent().getBundleExtra("bundle");
        user = new User(bundle.getString("email"), null, bundle.getString("name"), bundle.getString("image"), null, null, bundle.getString("typing"));
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
        binding.edtMess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    typingstatus("noOne");
                } else {
                    typingstatus(user.getEmail());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
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

    private void status(String status) {
        mRef.child("CSDL").child("User").child(mUser.getUid());
        HashMap<String, Object> map = new HashMap<>();
        map.put("isonline", status);
        mRef.child("CSDL").child("User").child(mUser.getUid()).updateChildren(map);
    }

    private void typingstatus(String typing) {
        mRef.child("CSDL").child("User").child(mUser.getUid());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("typing", typing);
        mRef.child("CSDL").child("User").child(mUser.getUid()).updateChildren(hashMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
        checktyping(user.getEmail());
        checkfriendonline(user.getEmail());
    }

    public void checktyping(String email) {
        mRef.child("CSDL").child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User user = dataSnapshot1.getValue(User.class);
                    if (user.getEmail().equals(email)) {
                        if (!user.getTyping().equals("noOne")) {
                            binding.typing.setText("...Đang nhập tin nhắn");
                        } else {
                            binding.typing.setText("");
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void checkfriendonline(String email)
    {
        mRef.child("CSDL").child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User user = dataSnapshot1.getValue(User.class);
                    if (user.getEmail().equals(email)) {

                        binding.onlineok.setText(user.getIsonline());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
