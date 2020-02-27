package com.example.appchatfb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appchatfb.generated.callback.OnClickListener;

public class Activity_Feedback_User extends AppCompatActivity {
    Button btn_Sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_request);
        btn_Sure=findViewById(R.id.btn_DongY);
        btn_Sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_Feedback_User.this, "Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
