package com.example.appchatfb.viewmodel;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appchatfb.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterViewModel extends ViewModel {
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    final String TAG="AAA";
    private String anhmd="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ-pHmAircFpVmN3Olp2rYsFc6cONka-nC24AFUPPU99sCdOu7J";
    // ...
    private MutableLiveData<Boolean> isRegisted = new MutableLiveData<>();
// Initialize Firebase Auth
    public void register(String name, String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        isRegisted.setValue(true);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("BBB", "createUserWithEmail:failure", task.getException());
                        isRegisted.setValue(false);
                    }
                });
    }

    public LiveData<Boolean> isRegisted(){
        return isRegisted;
    }

    public void addUser(String name,String email,String pass)
    {
        User user=new User(email,pass,name,anhmd,0);
        myRef.child("CSDL").child("User").push().setValue(user);
    }
}
