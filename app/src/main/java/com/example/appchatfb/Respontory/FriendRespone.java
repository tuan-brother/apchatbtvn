package com.example.appchatfb.Respontory;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

public class FriendRespone { DatabaseReference Dataref;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    MutableLiveData<ArrayList<User>> dataUser = new MutableLiveData<>();
    List<String> Friend=new ArrayList<>();
    String uId;

    public LiveData<ArrayList<User>> getEmailData() {
        Dataref= FirebaseDatabase.getInstance().getReference();
        ArrayList<User> requestFriend=new ArrayList<>();
        listFriend();
        Dataref.child("CSDL").child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<User> list = new ArrayList<>();
                if(Friend!=null) {
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
        //Log.d("AAA",String.valueOf(dataUser.getValue().size()));
        return dataUser;
    }
    public void listFriend()
    {
        uId = mAuth.getCurrentUser().getUid();
        Dataref.child("CSDL").child("User").child(uId).child("friend").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()==null)
                {

                }
                else {
                    Map<Long,String> dt=(HashMap<Long, String>)dataSnapshot.getValue();
                    Friend.addAll(dt.values());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
