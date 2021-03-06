package com.example.appchatfb.model;

import android.text.format.DateFormat;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Date;

public class ChatMessage {
    private String messageText;
    private String messageUser;
    private long messageTime;
    private String reciver;
    private String sender;
    public ChatMessage(String messageText, String messageUser,String reciver,String sender) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.reciver=reciver;
        this.sender=sender;
        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public ChatMessage(){

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String uRL)
    {
        Glide.with(view.getContext()).load(uRL).apply(new RequestOptions()).circleCrop().into(view);
    }
    public String fmTime() {
        String s=String.valueOf(DateFormat.format("(HH:mm:ss)", messageTime));
        return s;
    }
}
