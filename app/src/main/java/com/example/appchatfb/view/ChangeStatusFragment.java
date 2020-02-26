package com.example.appchatfb.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentChangeStatusBinding;


public class ChangeStatusFragment extends Fragment implements View.OnClickListener {
       private FragmentChangeStatusBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChangeStatusBinding.inflate(inflater,container,false);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        binding.switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.switch1.isChecked()){
                    Log.d("ccc","check");
                }
            }
        });
        return binding.getRoot();
    }



    @Override
    public void onClick(View v) {
        // comment
    }
}
