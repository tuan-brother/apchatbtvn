package com.example.appchatfb.Notification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APISerVice {
//    @Header(
//            {
//                    "Content-Type:application/json",
//                    "Authorization:key=AAAAXZ2xJNk:APA91bE1rDWbPklhD5DCD59a4OSS9ZM3zrD2gCpBS79Hkm_xzLIecZ0w7ZUTZEcBpN7bFWemwcHMyO7uXRSi6iTEkTeE9mece5OB32rR00eqiYVpW_KlMuFq8i19_17ojjsna5a8BV9l"
//            }
//    )
    @POST("fcm/send")
    Call<MyRespon> sendNotification(@Body Sender body);
}
