package com.example.appchatfb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentItemFriendBinding;
import com.example.appchatfb.model.User;

import java.util.ArrayList;

public class FmFriendAdapter extends RecyclerView.Adapter<FmFriendAdapter.ViewHolder> {
    Context context;
    ArrayList<User> users;

    public FmFriendAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
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
    }

    @Override
    public int getItemCount() {
        return users.size();
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
