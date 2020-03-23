package com.example.appchatfb.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.R;
import com.example.appchatfb.databinding.MessageBinding;
import com.example.appchatfb.databinding.MessagerightBinding;
import com.example.appchatfb.model.ChatMessage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ChatFriendAdapter extends RecyclerView.Adapter<ChatFriendAdapter.HolderView> {
    Context context;
    private static final int MSG_LEFT = 0;
    private static final int MSG_RIGHT = 1;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    ArrayList<ChatMessage> list = new ArrayList<>();
    String urlAnh;
    String lastMessage;

    public ChatFriendAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ChatFriendAdapter.HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewDataBinding binding;
        if (viewType ==MSG_LEFT) {
            binding = DataBindingUtil.inflate(inflater, R.layout.messageright, parent, false);
            return new HolderView((MessagerightBinding) binding);
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.message, parent, false);
            return new HolderView((MessageBinding) binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatFriendAdapter.HolderView holder, int position) {
        ChatMessage chat = list.get(position);
        if (holder.getItemViewType() == MSG_LEFT) {
            holder.bindright(chat);
        } else {
            holder.bind(chat,urlAnh);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {
        MessageBinding messageBinding;
        MessagerightBinding messagerightBinding;

        public HolderView(MessageBinding binding) {
            super(binding.getRoot());
            this.messageBinding = binding;
        }

        public HolderView(MessagerightBinding binding) {
            super(binding.getRoot());
            this.messagerightBinding = binding;
        }

        public void bind(ChatMessage chatMessage,String url) {
            messageBinding.setUrlAnh(url);
            messageBinding.setData(chatMessage);
        }

        public void bindright(ChatMessage chatMessage) {
            messagerightBinding.setData(chatMessage);
        }
    }

    public void setAdapter(ArrayList<ChatMessage> messageslist,String urAnh) {
        list = messageslist;
        urlAnh=urAnh;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getSender().equals(firebaseUser.getEmail())) {
            return MSG_RIGHT;
        } else {
            return MSG_LEFT;
        }
    }

    public void theLastMessage()
    {

    }
}
