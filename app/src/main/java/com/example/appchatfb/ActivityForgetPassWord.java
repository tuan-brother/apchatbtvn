package com.example.appchatfb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appchatfb.databinding.ActivityForgetPassWordBinding;
import com.example.appchatfb.interfacefunc.Event;
import com.example.appchatfb.viewmodel.ActivityDangNhapViewModel;

public class ActivityForgetPassWord extends AppCompatActivity implements Event {
    ActivityForgetPassWordBinding binding;
    ActivityDangNhapViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_forget_pass_word);
        viewModel=new ViewModelProvider(this).get(ActivityDangNhapViewModel.class);
        binding.setEvent(this);
    }

    @Override
    public void onClick() {
        viewModel.getPass(binding.etGetPass.getText().toString().trim());
        viewModel.isGetPass().observe(this,Boll->{
            if(Boll)
            {
                Toast.makeText(this, "Thông tin đã được gửi đi", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Email không tồn tại vui lòng nhập lại", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
