package com.example.appchatfb.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.Adapter.FmFriendAdapter;
import com.example.appchatfb.Adapter.FmRequestAdapter;
import com.example.appchatfb.R;
import com.example.appchatfb.interfacefunc.ClickAddfriend;
import com.example.appchatfb.model.User;

import java.util.ArrayList;

public class FragmentFriend extends Fragment {
    RecyclerView rc_Friend;
    LinearLayoutManager linearLayoutManager;
    FmFriendAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_friend,container,false);
        rc_Friend=view.findViewById(R.id.rc_fmFriend);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        adapter=new FmFriendAdapter(view.getContext(),FragmentRequest.listfriend);
        Toast.makeText(view.getContext(),String.valueOf(FragmentRequest.listfriend.size()), Toast.LENGTH_SHORT).show();
        rc_Friend.setLayoutManager(linearLayoutManager);
        rc_Friend.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}
