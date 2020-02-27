package com.example.appchatfb.Adapter;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("setavatar")
    public static void setAvatar(CircleImageView view,String s){
        Glide.with(view.getContext()).load(s)
                .centerCrop().into(view);
    }
}
