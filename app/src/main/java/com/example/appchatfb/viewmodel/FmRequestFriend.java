package com.example.appchatfb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchatfb.Respontory.RequestRespone;
import com.example.appchatfb.model.User;

import java.util.ArrayList;
import java.util.List;

public class FmRequestFriend extends ViewModel {
    private RequestRespone respone;
    private LiveData<ArrayList<User>> listUser;
    public LiveData<ArrayList<User>> getData()
    {
        respone=new RequestRespone();
        this.listUser=respone.getEmailData();
        return listUser;
    }

}
