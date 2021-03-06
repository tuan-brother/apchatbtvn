package com.example.appchatfb.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.CountDownTimer;
import android.renderscript.AllocationAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.appchatfb.Adapter.AllUserListAdapter;
import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentAllUserBinding;
import com.example.appchatfb.interfacefunc.ClickRequest;
import com.example.appchatfb.model.User;
import com.example.appchatfb.viewmodel.AllUserViewModel;
import com.example.appchatfb.viewmodel.RequestInviteViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.ListIterator;


public class AllUserFragment extends Fragment {
    private FragmentAllUserBinding binding;
    private AllUserListAdapter adapter;
    private ArrayList<User> dataAllUser;
    private ListIterator<User> alluse;
    private FirebaseAuth mAuth;
    CountDownTimer countDownTimer;
    RequestInviteViewModel rgViewModel;
    ClickRequest onClickRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllUserBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();
        rgViewModel = new ViewModelProvider(this).get(RequestInviteViewModel.class);
        onClickRequest = new ClickRequest() {
            @Override
            public void itemClickRequest(int position) {
                Toast.makeText(binding.getRoot().getContext(), "Đã gửi lời mời kết bạn!!", Toast.LENGTH_SHORT).show();
                rgViewModel.RequestFriend(dataAllUser.get(position));
            }
        };
        adapter = new AllUserListAdapter(onClickRequest);
        binding.rvContainer.setAdapter(adapter);
        binding.rvContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        AllUserViewModel viewModel = new ViewModelProvider(getActivity()).get(AllUserViewModel.class);
        viewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                dataAllUser = users;
                adapter.setUsers(dataAllUser);
            }
        });
        return binding.getRoot();
    }
}
