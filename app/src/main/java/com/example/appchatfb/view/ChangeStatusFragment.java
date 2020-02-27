package com.example.appchatfb.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentChangeStatusBinding;


public class ChangeStatusFragment extends DialogFragment implements View.OnClickListener {
       private FragmentChangeStatusBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChangeStatusBinding.inflate(inflater,container,false);
        binding.statusChangeCancel.setOnClickListener(this);
        binding.statusChangeSave.setOnClickListener(this);
        binding.etStatus.requestFocus();
        showKeyboard();
        return binding.getRoot();
    }


    public void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.status_change_cancel:
                getDialog().dismiss();
                break;
            case R.id.status_change_save:
            //todo do something to save user status
                break;

        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        closeKeyboard();
    }
}
