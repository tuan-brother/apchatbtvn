package com.example.appchatfb.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.R;
import com.example.appchatfb.databinding.AllUserItemRowBinding;
import com.example.appchatfb.model.User;

import java.util.ArrayList;

public class AllUserListAdapter extends RecyclerView.Adapter<AllUserListAdapter.ViewHolder> {
    private AllUserItemRowBinding binding;
    private ArrayList<User> users;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.all_user_item_row,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setUser(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users==null?0:users.size();
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private AllUserItemRowBinding binding ;
        public ViewHolder(@NonNull AllUserItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
