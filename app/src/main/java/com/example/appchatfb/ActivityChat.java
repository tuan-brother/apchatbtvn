package com.example.appchatfb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchatfb.databinding.ActivityChatBinding;
import com.example.appchatfb.generated.callback.OnClickListener;
import com.example.appchatfb.model.ChatMessage;
import com.example.appchatfb.model.User;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityChat extends AppCompatActivity {
    private FirebaseListAdapter<ChatMessage> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChatBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_chat);
        setSupportActionBar(binding.tbChatfriend);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle=getIntent().getBundleExtra("bundle");
        User user=new User(null,null,bundle.getString("name"),bundle.getString("image"),null,null);
        binding.setUser(user);
        binding.btnSendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance()
                        .getReference().child("CSDL").child("Chat").child(bundle.getString("name")).push()
                        .setValue(new ChatMessage(binding.edtMessage.getText().toString(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName())
                        );
                Toast.makeText(ActivityChat.this, "Chay đến đây rồi", Toast.LENGTH_SHORT).show();
                binding.edtMessage.setText("");
            }
        });

        adapter=new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.message,FirebaseDatabase.getInstance().getReference("CSDL/Chat/"+bundle.getString("name"))) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };
        binding.rcFmChat.setAdapter(adapter);
    }
}
