package com.example.appchatfb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
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
    private static final int MSG_LEFT=0;
    private static final int MSG_RIGHT=1;
    FirebaseUser firebaseUser;
    ArrayList<ChatMessage> list = new ArrayList<>();

    public ChatFriendAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ChatFriendAdapter.HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
            MessageBinding binding = DataBindingUtil.inflate(inflater, R.layout.message, parent, false);
            return new HolderView(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatFriendAdapter.HolderView holder, int position) {
        ChatMessage chat = list.get(position);
        holder.bind(chat);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {
        MessageBinding messageBinding;

        public HolderView(MessageBinding binding) {
            super(binding.getRoot());
            this.messageBinding = binding;
        }

        public void bind(ChatMessage chatMessage) {
            messageBinding.setData(chatMessage);
        }
    }

    public void setAdapter(ArrayList<ChatMessage> messageslist) {
        list = messageslist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if(list.get(position).getSender().equals(firebaseUser.getEmail()))
        {
            return MSG_RIGHT;
        }
        else {
            return MSG_LEFT;
        }
    }
}
