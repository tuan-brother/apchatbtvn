package com.example.appchatfb.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import android.text.format.DateFormat;

public class User {
    String email,pass,name,anh,status;
    Integer isonline;

    public User() {
    }

    public User(String email, String pass, String name, String anh, String status, Integer isonline) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.anh = anh;
        this.status = status;
        this.isonline = isonline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

        public Integer getIsonline() {
        return isonline;
    }

    public void setIsonline(Integer isonline) {
        this.isonline = isonline;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view,String uRL)
    {
        Glide.with(view.getContext()).load(uRL).apply(new RequestOptions()).circleCrop().into(view);
    }
}
