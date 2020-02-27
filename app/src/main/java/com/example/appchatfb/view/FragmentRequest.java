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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.Adapter.FmRequestAdapter;
import com.example.appchatfb.R;
import com.example.appchatfb.interfacefunc.ClickAddfriend;
import com.example.appchatfb.model.User;

import java.util.ArrayList;

public class FragmentRequest extends Fragment {
    RecyclerView rc_Request;
    LinearLayoutManager linearLayoutManager;
    FmRequestAdapter adapter;
    ArrayList<User> arrayListUser=new ArrayList<>();
    public static ArrayList<User> listfriend;
    ClickAddfriend clickAddfriend;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_request,container,false);
        addUser();
        anhXa();
        clickAddfriend=new ClickAddfriend() {
            @Override
            public void clickListener(int position) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setTitle("Add friend");
                builder.setMessage("Bạn có muốn làm bạn không?");
                //thoat ra neu kich ra ngoai
                builder.setCancelable(true);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), "Bạn đã đồng ý làm bạn"+String.valueOf(position), Toast.LENGTH_SHORT).show();
                        listfriend.add(arrayListUser.get(position));
                        arrayListUser.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        };
        rc_Request=view.findViewById(R.id.rc_fmRequest);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        adapter=new FmRequestAdapter(arrayListUser,view.getContext(),clickAddfriend);
        rc_Request.setLayoutManager(linearLayoutManager);
        rc_Request.setAdapter(adapter);
        return view;
    }
    public void addUser()
    {
        User user=new User("phungtuananhxy@gmail.com","kimminh","T.Anh",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ4a4uLmbg7v4omyIWp19DezolHQ-bVxp_iOpiX5G3KCPfgojVJ","helo",0);
        arrayListUser.add(user);
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
