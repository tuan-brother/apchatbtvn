package com.example.appchatfb.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityDangNhapViewModel extends ViewModel {
    private FirebaseAuth mAuth;
    private Boolean check=false;
    final String TAG="AAA";
    final MutableLiveData<Boolean> isLogSuccess=new MutableLiveData<>();
    public MutableLiveData<Boolean> checkLogIn(String email, String password) {
        mAuth=FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            isLogSuccess.setValue(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            isLogSuccess.setValue(false);
                        }

                        // ...

                    }
                });
Log.d("AAA",isLogSuccess.getValue()==true?"ok":"f");
        return isLogSuccess;
    }
    public boolean getPass(String email) {
        mAuth=FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            check=true;
                        }
                        else {
                            check=false;
                        }
                    }
                });
        return check;
    }
}
