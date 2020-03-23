package com.example.appchatfb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchatfb.R;
import com.example.appchatfb.databinding.FragmentItemChatBinding;
import com.example.appchatfb.interfacefunc.ClickAddfriend;
import com.example.appchatfb.model.ChatMessage;
import com.example.appchatfb.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FmChatAdapter extends RecyclerView.Adapter<FmChatAdapter.HolderView> {
    ArrayList<User> list;
    ClickAddfriend clickAddfriend;
    Context context;
    FragmentItemChatBinding binding;
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("CSDL/Chat/meomeo");
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String lastMessage;

    public FmChatAdapter(Context context, ClickAddfriend onClick) {
        this.context = context;
        this.clickAddfriend = onClick;
    }

    @NonNull
    @Override
    public FmChatAdapter.HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
         binding= DataBindingUtil.inflate(layoutInflater, R.layout.fragment_item_chat, parent, false);
        return new HolderView(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FmChatAdapter.HolderView holder, int position) {
        User user = list.get(position);
        holder.bind(user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAddfriend.clickListener(position);
            }
        });
        thelastMessage(holder,user.getEmail());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {
        FragmentItemChatBinding bindingchat;

        public HolderView(FragmentItemChatBinding binding) {
            super(binding.getRoot());
            this.bindingchat = binding;
        }

        public void bind(User user) {
            bindingchat.setUser(user);
        }
    }

    public void setUser(ArrayList<User> listUser) {
        this.list = listUser;
        notifyDataSetChanged();
    }

    public void thelastMessage(HolderView view,String email) {
        lastMessage="default";
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ChatMessage message = dataSnapshot1.getValue(ChatMessage.class);
                    if (message.getReciver().equals(user.getEmail()) && message.getSender().equals(email)
                            || message.getSender().equals(user.getEmail()) && message.getReciver().equals(email)) {
                        lastMessage = message.getMessageText();
                    }
                }
                switch (lastMessage)
                {
                    case "default":
                        view.bindingchat.tvLastMessage.setText("No mess");
                        break;
                    default:
                        view.bindingchat.tvLastMessage.setText(lastMessage);
                        break;
                }
                lastMessage="default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
