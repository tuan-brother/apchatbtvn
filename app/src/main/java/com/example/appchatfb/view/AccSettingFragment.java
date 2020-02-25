package com.example.appchatfb.view;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentAccSettingBinding;
import com.example.appchatfb.interfacefunc.AccSettingEvent;

import gun0912.tedimagepicker.builder.TedImagePicker;
import gun0912.tedimagepicker.builder.listener.OnSelectedListener;

/**
 * Open seting acc: change avatar, status ...
 */
public class AccSettingFragment extends Fragment implements AccSettingEvent {
    private FragmentAccSettingBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_acc_setting,container,false);
        binding.setEvent(this);

        return binding.getRoot();
    }


    @Override
    public void changeAvatar() {
        TedImagePicker.with(getContext()).start(new OnSelectedListener() {
            @Override
            public void onSelected(Uri uri) {
                Glide.with(getActivity()).load(uri).into(binding.imgAvatar);
            }
        });
    }

    @Override
    public void changeStatus() {
        ChangeStatusFragment fragment = new ChangeStatusFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,fragment).addToBackStack(null).commit();
    }
}
