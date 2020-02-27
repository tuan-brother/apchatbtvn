package com.example.appchatfb.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.renderscript.AllocationAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.appchatfb.Adapter.AllUserListAdapter;
import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentAllUserBinding;
import com.example.appchatfb.model.User;
import com.example.appchatfb.viewmodel.AllUserViewModel;

import java.util.ArrayList;


public class AllUserFragment extends Fragment {
    private FragmentAllUserBinding binding;
    private AllUserListAdapter adapter;
    private ArrayList<User> users;
    private AllUserViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllUserBinding.inflate(inflater, container, false);
        adapter = new AllUserListAdapter();
        binding.rvContainer.setAdapter(adapter);
        binding.rvContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(getActivity()).get(AllUserViewModel.class);
        viewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                adapter.setUsers(users);
            }
        });
        return binding.getRoot();
    }
}
