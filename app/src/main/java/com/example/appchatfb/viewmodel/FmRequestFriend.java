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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FmRequestFriend extends ViewModel {
    private RequestRespone respone;
    private LiveData<ArrayList<User>> listUser;
    DatabaseReference Dataref = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String uId = mAuth.getCurrentUser().getUid();

    public LiveData<ArrayList<User>> getData() {
        respone = new RequestRespone();
        this.listUser = respone.getEmailData();
        return listUser;
    }

    public void addFriend(String email) {
        Calendar time = Calendar.getInstance();
        Dataref.child("CSDL").child("User").child(uId).child("friend").child(String.valueOf(time.getTimeInMillis())).setValue(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Dataref.child("CSDL").child("User").child(uId).child("requestfriend").removeValue();
            }
        });
    }

    public void friendly2(String email) {
        Calendar timeer = Calendar.getInstance();
        Dataref.child("CSDL").child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String key = data.getKey();
                    if (data.child("email").getValue().equals(email)) {
                        Dataref.child("CSDL").child("User").child(key).child("friend").child(String.valueOf(timeer.getTimeInMillis())).setValue(mAuth.getCurrentUser().getEmail());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
