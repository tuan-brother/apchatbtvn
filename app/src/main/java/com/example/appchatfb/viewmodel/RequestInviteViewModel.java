package com.example.appchatfb.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;


import com.example.appchatfb.model.User;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestInviteViewModel extends ViewModel {
    DatabaseReference Dataref = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    String uId;
    public void RequestFriend(User user)
    {
        Calendar time=Calendar.getInstance();
            Dataref.child("CSDL").child("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot data:dataSnapshot.getChildren())
                    {
                        String key=data.getKey();
                       if(data.getValue(User.class).getEmail().equals(user.getEmail()))
                       {
                            Dataref.child("CSDL").child("User").child(key).child("requestfriend").child(String.valueOf(time.getTimeInMillis())).setValue(mAuth.getCurrentUser().getEmail());
                            return;
                       }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }

}
