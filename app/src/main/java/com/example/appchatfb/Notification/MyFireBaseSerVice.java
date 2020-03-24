package com.example.appchatfb.Notification;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.appchatfb.ActivityChat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseSerVice extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String sented=remoteMessage.getData().get("sented");
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null&&sented.equals(firebaseUser.getUid()))
        {
            senNotification(remoteMessage);
        }
    }

    private void senNotification(RemoteMessage remoteMessage) {
        String user=remoteMessage.getData().get("user");
        String icon=remoteMessage.getData().get("icon");
        String title=remoteMessage.getData().get("title");
        String body=remoteMessage.getData().get("body");
        RemoteMessage.Notification notification=remoteMessage.getNotification();
        int j=Integer.parseInt(user.replaceAll("[\\D]",""));
        Intent intent=new Intent(this, ActivityChat.class);
        Bundle bundle=new Bundle();
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String token= FirebaseInstanceId.getInstance().getToken();
        if(firebaseUser!=null)
        {
            updateToken(token);
        }
    }

    private void updateToken(String refreshToken) {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mRef= FirebaseDatabase.getInstance().getReference("Tokens");
        Token token=new Token(refreshToken);
        mRef.child(firebaseUser.getUid()).setValue(token);
    }


}
