package com.example.appchatfb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appchatfb.databinding.ActivityRegisterBinding;
import com.example.appchatfb.interfacefunc.Event;
import com.example.appchatfb.viewmodel.LoginViewModel;

public class Register extends AppCompatActivity implements Event {
    private ActivityRegisterBinding binding;
    private LoginViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setEvent(this);
    }

    @Override
    public void onClick() {
        if(binding.regisUser.getText().toString().trim().equals("")&&binding.etEmail.getText().toString().trim().equals("")&&binding.etPass.getText().toString().trim().equals(""))
        {
            Toast.makeText(this, "Không để trống dữ liệu", Toast.LENGTH_SHORT).show();
        }
        else {
            if (viewModel.register(binding.regisUser.getText().toString().trim(), binding.etEmail.getText().toString().trim(), binding.etPass.getText().toString().trim())) {
                Toast.makeText(this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                viewModel.addUser(binding.regisUser.getText().toString().trim(), binding.etEmail.getText().toString().trim(), binding.etPass.getText().toString().trim());
                Intent intent = new Intent(Register.this, DangNhap.class);
                startActivity(intent);
            }
        }
    }
}
