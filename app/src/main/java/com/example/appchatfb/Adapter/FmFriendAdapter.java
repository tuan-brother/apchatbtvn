package com.example.appchatfb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.ActivityChat;
import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentItemFriendBinding;
import com.example.appchatfb.interfacefunc.ClickChat;
import com.example.appchatfb.model.User;

import java.util.ArrayList;

public class FmFriendAdapter extends RecyclerView.Adapter<FmFriendAdapter.ViewHolder> {
    Context context;
    ArrayList<User> users;
    ClickChat onClick;
    public FmFriendAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FmFriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        FragmentItemFriendBinding binding= DataBindingUtil.inflate(inflater, R.layout.fragment_item_friend,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FmFriendAdapter.ViewHolder holder, int position) {
        User user=users.get(position);
        holder.bind(user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ActivityChat.class);
                Bundle bundle=new Bundle();
                bundle.putString("name",user.getName());
                bundle.putString("image",user.getAnh());
                bundle.putString("email",user.getEmail());
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users==null?0:users.size();
    }

    public void setUser(ArrayList<User> listfriend)
    {
        this.users=listfriend;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        FragmentItemFriendBinding binding;
        public ViewHolder(FragmentItemFriendBinding itemFriendBinding) {
            super(itemFriendBinding.getRoot());
            this.binding=itemFriendBinding;
        }

        public void bind(User user)
        {
            binding.setUser(user);
        }
    }
}
