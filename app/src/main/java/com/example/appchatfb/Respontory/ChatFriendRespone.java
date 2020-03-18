package com.example.appchatfb.Respontory;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchatfb.model.ChatMessage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFriendRespone {
    DatabaseReference mRef;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    MutableLiveData<ArrayList<ChatMessage>> data=new MutableLiveData<>();
    String uID=mAuth.getCurrentUser().getEmail();
    ArrayList<ChatMessage> listChat;
    public LiveData<ArrayList<ChatMessage>> getdata()
    {
        return data;
    }
    public void dataChat(String email)
    {
        mRef= FirebaseDatabase.getInstance().getReference("CSDL/Chat/meomeo");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listChat=new ArrayList<>();
                for(DataSnapshot child:dataSnapshot.getChildren())
                {
                    ChatMessage chat=child.getValue(ChatMessage.class);
                    if(chat.getReciver().equals(email)&&chat.getSender().equals(uID)||chat.getSender().equals(email)&&chat.getReciver().equals(uID))
                    {
                        listChat.add(chat);
                    }
                }
                data.setValue(listChat);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
