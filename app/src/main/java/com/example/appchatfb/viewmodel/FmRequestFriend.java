package com.example.appchatfb.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchatfb.Respontory.RequestRespone;
import com.example.appchatfb.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class FmRequestFriend extends ViewModel {
    private RequestRespone respone;
    private LiveData<ArrayList<User>> listUser;
    DatabaseReference Dataref = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String uId;
    Map<String, String> dt = new HashMap<>();
    public LiveData<ArrayList<User>> getData()
    {
        respone=new RequestRespone();
        this.listUser=respone.getEmailData();
        return listUser;
    }

    public void addFriend(User user)
    {
        uId = mAuth.getCurrentUser().getUid();
        Calendar time = Calendar.getInstance();
        Dataref.child("CSDL").child("User").child(uId).child("friend").child(String.valueOf(time.getTimeInMillis())).setValue(user.getEmail());
        //lỗi chỗ này
        Dataref.child("CSDL").child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    String s=data.getKey();
                    if(data.getValue(User.class).getEmail().equals(user.getEmail()))
                    {
                        Dataref.child("CSDL").child("User").child(s).child("friend").child(String.valueOf(time.getTimeInMillis())).setValue(mAuth.getCurrentUser().getEmail());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void deleteRequestFriend(User user) {
        uId = mAuth.getCurrentUser().getUid();
        Dataref.child("CSDL").child("User").child(uId).child("requestfriend").setValue(null);
        getdata();
        for (String key : dt.keySet()) {
            if (dt.get(key).equals(user.getEmail())) {
            }
        }
    }

    public void getdata() {
        Dataref.child("CSDL").child("User").child(uId).child("requestfriend").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dt = (HashMap<String, String>) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
