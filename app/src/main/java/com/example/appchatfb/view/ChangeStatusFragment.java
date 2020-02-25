package com.example.appchatfb.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentChangeStatusBinding;


public class ChangeStatusFragment extends Fragment implements View.OnClickListener {
   private FragmentChangeStatusBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChangeStatusBinding.inflate(inflater,container,false);
        return binding.getRoot();
        //binding.back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
