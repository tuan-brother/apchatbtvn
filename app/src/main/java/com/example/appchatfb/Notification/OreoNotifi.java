package com.example.appchatfb.Notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelUuid;

public class OreoNotifi extends ContextWrapper {
    private static final String Channel_id="com.example.appchatfb";
    private static final String Channel_name="appchatfb";
    private NotificationManager notifimanager;
    public OreoNotifi(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel=new NotificationChannel(Channel_id,Channel_name,NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(false);
        channel.enableVibration(true);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager()
    {
        if(notifimanager==null)
        {
            notifimanager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notifimanager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder getOreNotifi(String title, String body, PendingIntent pendingIntent, Uri soundUri, String icon)
    {
        return new Notification.Builder(getApplicationContext(),Channel_id)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(Integer.parseInt(icon))
                .setSound(soundUri)
                .setAutoCancel(true);

    }
}
