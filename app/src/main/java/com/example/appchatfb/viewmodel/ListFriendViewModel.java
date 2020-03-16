package com.example.appchatfb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchatfb.Respontory.FriendRespone;
import com.example.appchatfb.model.User;

import java.util.ArrayList;


public class ListFriendViewModel extends ViewModel {
    FriendRespone friendRespone;
    LiveData<ArrayList<User>> listfriend;
    public LiveData<ArrayList<User>> getListFriend() {
        friendRespone=new FriendRespone();
        this.listfriend=friendRespone.getEmailData();
        return listfriend;
    }
}
