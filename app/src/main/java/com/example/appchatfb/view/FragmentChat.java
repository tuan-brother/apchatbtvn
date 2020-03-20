package com.example.appchatfb.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appchatfb.ActivityChat;
import com.example.appchatfb.Adapter.FmChatAdapter;
import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentChatBinding;
import com.example.appchatfb.interfacefunc.ClickAddfriend;
import com.example.appchatfb.model.User;
import com.example.appchatfb.viewmodel.FmChatViewModel;

import java.util.ArrayList;

public class FragmentChat extends Fragment {
    ArrayList<User> userArrayList=new ArrayList<>();
    FmChatAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    FmChatViewModel model;
    ClickAddfriend clickAddfriend;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChatBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_chat,container,false);
        clickAddfriend=new ClickAddfriend() {
            @Override
            public void clickListener(int position) {
                User user=userArrayList.get(position);
                Intent intent=new Intent(binding.getRoot().getContext(), ActivityChat.class);
                Bundle bundle=new Bundle();
                bundle.putString("email",user.getEmail());
                bundle.putString("name",user.getName());
                bundle.putString("image",user.getAnh());
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        };
        linearLayoutManager=new LinearLayoutManager(binding.getRoot().getContext());
        adapter=new FmChatAdapter(getContext(),clickAddfriend);
        binding.rcFmChat.setLayoutManager(linearLayoutManager);
        binding.rcFmChat.setAdapter(adapter);
        //viewmodel
        model=new ViewModelProvider(this).get(FmChatViewModel.class);
        model.getData().observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                userArrayList=users;
                Toast.makeText(binding.getRoot().getContext(),""+users.size(), Toast.LENGTH_SHORT).show();
                adapter.setUser(users);
            }
        });
        return binding.getRoot();
    }
}
