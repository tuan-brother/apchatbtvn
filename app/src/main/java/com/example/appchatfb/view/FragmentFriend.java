package com.example.appchatfb.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.Adapter.FmFriendAdapter;
import com.example.appchatfb.Adapter.FmRequestAdapter;
import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentFriendBinding;
import com.example.appchatfb.interfacefunc.ClickAddfriend;
import com.example.appchatfb.model.User;
import com.example.appchatfb.viewmodel.ListFriendViewModel;

import java.util.ArrayList;

public class FragmentFriend extends Fragment {
    RecyclerView rc_Friend;
    LinearLayoutManager linearLayoutManager;
    FmFriendAdapter adapter;
    ListFriendViewModel modelfriend;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentFriendBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_friend,container,false);
        linearLayoutManager=new LinearLayoutManager(binding.getRoot().getContext());
        adapter=new FmFriendAdapter(binding.getRoot().getContext());
        binding.rcFmFriend.setLayoutManager(linearLayoutManager);
        binding.rcFmFriend.setAdapter(adapter);
        //viewmodel
        modelfriend=new ViewModelProvider(this).get(ListFriendViewModel.class);
        modelfriend.getListFriend().observe(getViewLifecycleOwner(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                adapter.setUser(users);
            }
        });
        return binding.getRoot();
    }
}
