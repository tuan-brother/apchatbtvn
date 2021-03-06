package com.example.appchatfb.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.Adapter.FmRequestAdapter;
import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentRequestBinding;
import com.example.appchatfb.interfacefunc.ClickAddfriend;
import com.example.appchatfb.model.User;
import com.example.appchatfb.viewmodel.FmRequestFriend;
import com.example.appchatfb.viewmodel.RequestInviteViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentRequest extends Fragment {
    LinearLayoutManager linearLayoutManager;
    FmRequestAdapter adapter;
    FmRequestFriend model;
    String uId;
    DatabaseReference Dataref = FirebaseDatabase.getInstance().getReference();
    ArrayList<User> list;
    ClickAddfriend onClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        FragmentRequestBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_request, container, false);
        onClick = new ClickAddfriend() {
            @Override
            public void clickListener(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(binding.getRoot().getContext());
                builder.setTitle("Add friend");
                builder.setMessage("Bạn Có muốn làm bạn với người này không?");
                //thoat ra neu kich ra ngoai
                builder.setCancelable(true);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(binding.getRoot().getContext(), "Kết bạn thành công", Toast.LENGTH_SHORT).show();
                        model.addFriend(list.get(position).getEmail());
                        model.friendly2(list.get(position).getEmail());
                        list.remove(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(binding.getRoot().getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        };
        linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        adapter = new FmRequestAdapter(binding.getRoot().getContext(), onClick);
        binding.rcFmRequest.setLayoutManager(linearLayoutManager);
        binding.rcFmRequest.setAdapter(adapter);
        //model requestfriend
        model = new ViewModelProvider(this).get(FmRequestFriend.class);
        model.getData().observe(getViewLifecycleOwner(), data -> {
            list = data;
            adapter.setUsers(list);
        });

        return binding.getRoot();
    }
}
