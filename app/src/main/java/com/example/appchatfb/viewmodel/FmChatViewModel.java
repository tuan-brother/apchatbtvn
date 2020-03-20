package com.example.appchatfb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchatfb.Respontory.ChatRespone;
import com.example.appchatfb.model.User;

import java.util.ArrayList;

public class FmChatViewModel extends ViewModel {
    LiveData<ArrayList<User>> data;
    ChatRespone chatRespone;
    public LiveData<ArrayList<User>> getData()
    {
        chatRespone=new ChatRespone();
        this.data=chatRespone.listUser();
        return data;
    }
}
