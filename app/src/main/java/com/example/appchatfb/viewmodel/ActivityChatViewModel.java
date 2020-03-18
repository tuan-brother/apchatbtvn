package com.example.appchatfb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchatfb.Respontory.ChatFriendRespone;
import com.example.appchatfb.model.ChatMessage;

import java.util.ArrayList;

public class ActivityChatViewModel extends ViewModel {
    ChatFriendRespone respone;
    LiveData<ArrayList<ChatMessage>> data;
    public LiveData<ArrayList<ChatMessage>> dataChat(String email)
    {
        respone=new ChatFriendRespone();
        respone.dataChat(email);
        data=respone.getdata();
        return data;
    }
}
