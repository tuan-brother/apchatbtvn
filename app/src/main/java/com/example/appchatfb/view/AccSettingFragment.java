package com.example.appchatfb.view;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentAccSettingBinding;
import com.example.appchatfb.interfacefunc.AccSettingEvent;
import com.example.appchatfb.viewmodel.AccSettingViewModel;


/**
 * Open seting acc: change avatar, status ...
 */
public class AccSettingFragment extends Fragment implements AccSettingEvent {
    private FragmentAccSettingBinding binding;
    private AccSettingViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_acc_setting, container, false);
        binding.setEvent(this);
        viewModel = new ViewModelProvider(getActivity()).get(AccSettingViewModel.class);
        viewModel.getAvatar().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Glide.with(getContext()).load(s)
                        .centerCrop().into(binding.imgAvatar);
            }
        });
        binding.tvUserName.setText(viewModel.getName().getValue());
        return binding.getRoot();
    }


    @Override
    public void changeAvatar() {
        PickphotoDialog dialog = new PickphotoDialog();
        dialog.show(getFragmentManager(),null);
    }

    @Override
    public void changeStatus() {
        ChangeStatusFragment dialog = new ChangeStatusFragment();
        dialog.show(getFragmentManager(),null);
    }
}
