package com.example.appchatfb.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentChatBinding;

public class FragmentChat extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChatBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_chat,container,false);
        return binding.getRoot();
    }
}
