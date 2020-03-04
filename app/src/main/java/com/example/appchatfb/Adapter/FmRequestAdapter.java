package com.example.appchatfb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentItemRequestBinding;
import com.example.appchatfb.interfacefunc.ClickAddfriend;
import com.example.appchatfb.model.User;

import java.util.ArrayList;

public class FmRequestAdapter extends RecyclerView.Adapter<FmRequestAdapter.ViewHolder> {
    ArrayList<User> userArrayList;
    Context context;
    ClickAddfriend itemClickAddFriend;
    public FmRequestAdapter(Context context,ClickAddfriend clickAddfriend) {
        this.context = context;
        this.itemClickAddFriend=clickAddfriend;
    }

    @NonNull
    @Override
    public FmRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        FragmentItemRequestBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_item_request,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FmRequestAdapter.ViewHolder holder, int position) {
        User user=userArrayList.get(position);
        holder.bind(user);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemClickAddFriend.clickListener(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList==null?0:userArrayList.size();
    }

    public void setUsers(ArrayList<User> users) {
        this.userArrayList = users;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        FragmentItemRequestBinding itemRequestBinding;
        public ViewHolder(FragmentItemRequestBinding binding) {
            super(binding.getRoot());
            this.itemRequestBinding=binding;
        }
        public void bind(User user)
        {
            itemRequestBinding.setUser(user);
        }
    }
}

