package com.example.appchatfb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.appchatfb.databinding.ActivityDangNhapBinding;
import com.example.appchatfb.view.MainActivity;
import com.example.appchatfb.viewmodel.ActivityDangNhapViewModel;

public class ActivityLogin extends AppCompatActivity implements ClickDangNhap {
    private ActivityDangNhapViewModel viewModel;
    private ActivityDangNhapBinding binding;
    MutableLiveData<Boolean> check = new MutableLiveData<>();
    private boolean noReply = false;
    CountDownTimer Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dang_nhap);
        viewModel = new ViewModelProvider(this).get(ActivityDangNhapViewModel.class);
        binding.setItemclick(this);
    }

    @Override
    public void clickDangNhap() {
        if (binding.etEmail.getText().toString().trim().equals("") || binding.etPass.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Không để trống dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.checkLogIn(binding.etEmail.getText().toString().trim(), binding.etPass.getText().toString().trim()).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        if (!noReply) {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Timer = new CountDownTimer(3000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {

                                }

                                @Override
                                public void onFinish() {
                                    Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                                    noReply = true;
                                    startActivity(intent);
                                    binding.etPass.setText("");
                                }
                            }.start();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        noReply = false;
    }

    @Override
    public void chuyenmanhinh() {
        Intent intent = new Intent(ActivityLogin.this, Register.class);
        startActivity(intent);
    }

    @Override
    public void chuyenMHForgetPass() {
        Intent intent = new Intent(ActivityLogin.this, ActivityForgetPassWord.class);
        startActivity(intent);
    }

}
