package com.example.appchatfb.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchatfb.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listFriendViewModel extends ViewModel {
    DatabaseReference Dataref;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    MutableLiveData<ArrayList<User>> dataUser = new MutableLiveData<>();
    List<String> Friend = new ArrayList<>();
    String uId;

    public void getEmailData() {
        Dataref = FirebaseDatabase.getInstance().getReference();
        ArrayList<User> requestFriend = new ArrayList<>();
        listFriend();
        Dataref.child("CSDL").child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<User> list = new ArrayList<>();
                if (Friend != null) {
                    for (int i = 0; i < Friend.size(); i++) {
                        String s = String.valueOf(Friend.get(i));
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            if (data.getValue(User.class).getEmail().equals(s)) {
                                list.add(data.getValue(User.class));
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
    }

    public void listFriend() {
        uId = mAuth.getCurrentUser().getUid();
        Dataref.child("CSDL").child("User").child(uId).child("friend").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {

                } else {
                    Map<String, String> dt = (HashMap<String, String>) dataSnapshot.getValue();
                    Friend.addAll(dt.values());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LiveData<ArrayList<User>> getListFriend() {
        return dataUser;
    }
}