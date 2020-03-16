package com.example.appchatfb.viewmodel;

import android.util.Log;

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

public class AllUserViewModel extends ViewModel {
    private MutableLiveData<ArrayList<User>> users = new MutableLiveData<>();
    private FirebaseDatabase database;
    private DatabaseReference reference;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    public AllUserViewModel() {
        super();
        initUserFromFireBase();
    }

    public LiveData<ArrayList<User>> getUsers() {
        return users;
    }

    private void initUserFromFireBase() {
        String mail = mAuth.getCurrentUser().getEmail();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("CSDL/User");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<User> a = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if(!data.getValue(User.class).getEmail().equals(mail))
                    {
                        a.add(data.getValue(User.class));
                    }
                }
                users.setValue(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
