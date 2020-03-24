package com.example.appchatfb;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceMessage extends Service {
    public ServiceMessage() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
