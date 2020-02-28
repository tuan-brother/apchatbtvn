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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.Adapter.FmRequestAdapter;
import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentRequestBinding;
import com.example.appchatfb.interfacefunc.ClickAddfriend;
import com.example.appchatfb.model.User;

import java.util.ArrayList;

public class FragmentRequest extends Fragment {
    RecyclerView rc_Request;
    LinearLayoutManager linearLayoutManager;
    FmRequestAdapter adapter;
    public static ArrayList<User> listfriend;
    ClickAddfriend clickAddfriend;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentRequestBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_request,container,false);
        //addUser();
        anhXa();
        clickAddfriend=new ClickAddfriend() {
            @Override
            public void clickListener(int position) {
                AlertDialog.Builder builder=new AlertDialog.Builder(binding.getRoot().getContext());
                builder.setTitle("Add friend");
                builder.setMessage("Bạn có muốn làm bạn không?");
                //thoat ra neu kich ra ngoai
                builder.setCancelable(true);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listfriend.add(AllUserFragment.userRequest.get(position));
                        AllUserFragment.userRequest.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(binding.getRoot().getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        };
        rc_Request=binding.rcFmRequest;
        linearLayoutManager=new LinearLayoutManager(binding.getRoot().getContext());
        adapter=new FmRequestAdapter(AllUserFragment.userRequest,binding.getRoot().getContext(),clickAddfriend);
        rc_Request.setLayoutManager(linearLayoutManager);
        rc_Request.setAdapter(adapter);
        return binding.getRoot();
    }
    public void anhXa()
    {
        if(listfriend!=null)
        {

        }
        else {
            listfriend=new ArrayList<>();
        }
    }
}
