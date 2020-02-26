package com.example.appchatfb.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

public class AccSettingViewModel extends ViewModel {
    private MutableLiveData<String> avatar = new MutableLiveData<>();
    private MutableLiveData<Boolean> isOnline = new MutableLiveData<Boolean>(true);
    private MutableLiveData<String> name = new MutableLiveData<>();
    private String email ;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private DatabaseReference mDatabase;
    private StorageReference mStorage;

    public LiveData<String> getAvatar() {
        return avatar;
    }

    public LiveData<String> getName() {
        return name;
    }

    public void setUserFromEmail(String email) {
        this.email = email;
        mDatabase = FirebaseDatabase.getInstance().getReference("CSDL");
        mDatabase.child("User").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()
                ) {
                   if(data.child("email").getValue().toString().equals(email)){
                       avatar.setValue(data.child("anh").getValue().toString());
                       name.setValue(data.child("name").getValue().toString());
                       isOnline.setValue(data.child("isonline").getValue(Long.class)==0?false:true);
                   }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setAvatar(Bitmap _avatar) {
        mStorage = storage.getReference("avatar");
        Calendar time = Calendar.getInstance();
        final StorageReference mountainsRef = mStorage.child("avatar" + time.getTimeInMillis() + ".png");
        // Get the data from an ImageView as bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        _avatar.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        mDatabase.child("User").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                            if(data.child("email").getValue().toString().equals(email)){
                               data.getRef().child("anh").setValue(uri.toString());
                               avatar.setValue(uri.toString());
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                    }
                });
            }
        });

}}
