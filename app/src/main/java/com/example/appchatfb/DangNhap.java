package com.example.appchatfb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appchatfb.databinding.ActivityDangNhapBinding;
import com.example.appchatfb.view.FragmentRequest;
import com.example.appchatfb.view.MainActivity;
import com.example.appchatfb.viewmodel.ActivityDangNhapViewModel;

public class DangNhap extends AppCompatActivity implements ClickDangNhap {
    private ActivityDangNhapViewModel viewModel;
    private ActivityDangNhapBinding binding;
    MutableLiveData<Boolean> check=new MutableLiveData<>();
    private boolean noReply = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_dang_nhap);
        viewModel=new ViewModelProvider(this).get(ActivityDangNhapViewModel.class);
        binding.setItemclick(this);
    }

    @Override
    public void clickDangNhap() {
        if (binding.etEmail.getText().toString().trim().equals("") && binding.etPass.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Không để trống dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.checkLogIn(binding.etEmail.getText().toString().trim(), binding.etPass.getText().toString().trim()).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean ){
                        if(!noReply) {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangNhap.this,MainActivity.class);
                            intent.putExtra("email",binding.etEmail.getText().toString());
                            noReply = true;
                            startActivity(intent);
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        noReply=false;
    }

    @Override
    public void chuyenmanhinh() {
        Intent intent=new Intent(DangNhap.this,Register.class);
        startActivity(intent);
    }

    @Override
    public void chuyenMHForgetPass() {
        Intent intent=new Intent(DangNhap.this,ActivityForgetPassWord.class);
        startActivity(intent);
    }
}
