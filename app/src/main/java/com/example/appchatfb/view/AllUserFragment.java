package com.example.appchatfb.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.renderscript.AllocationAdapter;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class AllUserFragment extends Fragment {
    private FragmentAllUserBinding binding;
    private AllUserListAdapter adapter;
    private ArrayList<User> dataAllUser;
    private DatabaseReference DataRef=FirebaseDatabase.getInstance().getReference();
    public static ArrayList<User> userRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllUserBinding.inflate(inflater, container, false);
        dataRequest();
        //thoat ra neu kich ra ngoai
        //                        listfriend.add(arrayListUser.get(position));
        //                        arrayListUser.remove(position);
        //                        adapter.notifyDataSetChanged();
        ClickRequest onClickRequest = new ClickRequest() {
            @Override
            public void itemClickRequest(int position) {
                Toast.makeText(binding.getRoot().getContext(), "" + String.valueOf(position), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(binding.getRoot().getContext());
                builder.setTitle("Add friend");
                builder.setMessage("Bạn có muốn làm bạn không?");
                //thoat ra neu kich ra ngoai
                builder.setCancelable(true);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userRequest.add(dataAllUser.get(position));
                        dataAllUser.remove(position);
                        adapter.notifyDataSetChanged();
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
        adapter = new AllUserListAdapter(onClickRequest);
        binding.rvContainer.setAdapter(adapter);
        binding.rvContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        AllUserViewModel viewModel = new ViewModelProvider(getActivity()).get(AllUserViewModel.class);
        viewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                dataAllUser=users;
                adapter.setUsers(dataAllUser);
            }
        });
        return binding.getRoot();
    }
    public void dataRequest()
    {
        if(userRequest!=null)
        {

        }
        else {
            userRequest=new ArrayList<>();
        }
    }
}
