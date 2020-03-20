package com.example.appchatfb.Respontory;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appchatfb.model.ChatMessage;
import com.example.appchatfb.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ChatRespone {
    DatabaseReference Dataref = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    MutableLiveData<ArrayList<User>> dataUser = new MutableLiveData<>();
    List<String> listEmail;
    ArrayList<User> list;
    ArrayList<User> listTotal;
    String uID = mAuth.getCurrentUser().getEmail();

    public LiveData<ArrayList<User>> getEmailData() {
        Dataref = FirebaseDatabase.getInstance().getReference();
        Dataref.child("CSDL").child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (listEmail != null) {
                    for (int i = 0; i < listEmail.size(); i++) {
                        String s = String.valueOf(listEmail.get(i));
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            User user = data.getValue(User.class);
                            if (user.getEmail().equals(s)) {
                                if (list.size() != 0) {
                                    for (User user1 : list) {

                                    }
                                }
                                else {
                                    list.add(data.getValue(User.class));
                                    return;
                                }
                            }
                        }
                    }
                    dataUser.setValue(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return dataUser;
    }
//    public void listFriend()
//    {
//        uId = mAuth.getCurrentUser().getUid();
//        Dataref.child("CSDL").child("User").child(uId).child("friend").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.getValue()==null)
//                {
//
//                }
//                else {
//                    Map<Long,String> dt=(HashMap<Long, String>)dataSnapshot.getValue();
//                    Friend.addAll(dt.values());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    public LiveData<ArrayList<User>> listUser() {
        Dataref.child("CSDL").child("Chat").child("meomeo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listEmail = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ChatMessage chat = data.getValue(ChatMessage.class);
                    if (chat.getSender().equals(uID)) {
                        listEmail.add(chat.getReciver());
                    }
                    if (chat.getReciver().equals(uID)) {
                        listEmail.add(chat.getSender());
                    }
                }
                readchat();
                dataUser.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return dataUser;
    }

    private void readchat() {
        list = new ArrayList<>();
        Dataref.child("CSDL").child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    for (String email : listEmail) {
                        if (user.getEmail().equals(email)) {
                            ListIterator<User> itr = list.listIterator();
                            if (itr.hasNext()) {
                                while (itr.hasNext()) {
                                    User user1 = itr.next();
                                    if (!user.getEmail().equals(user1.getEmail())) {
                                        itr.add(user);
                                    }
                                }
                            } else {
                                itr.add(user);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
