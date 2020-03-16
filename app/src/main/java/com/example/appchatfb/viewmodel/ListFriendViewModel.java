package com.example.appchatfb.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchatfb.Respontory.FriendRespone;
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

public class ListFriendViewModel extends ViewModel {
    FriendRespone friendRespone;
    LiveData<ArrayList<User>> listfriend;
    public LiveData<ArrayList<User>> getListFriend() {
        friendRespone=new FriendRespone();
        this.listfriend=friendRespone.getEmailData();
        return listfriend;
    }

}
