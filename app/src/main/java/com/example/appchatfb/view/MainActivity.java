package com.example.appchatfb.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appchatfb.Adapter.ViewPagerAdapter;
import com.example.appchatfb.R;
import com.example.appchatfb.databinding.ActivityMainBinding;
import com.example.appchatfb.viewmodel.AccSettingViewModel;
import com.example.appchatfb.viewmodel.AllUserViewModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ViewPagerAdapter adapter;
    private FragmentManager fragmentManager;
    private AccSettingViewModel viewModel;
    private AllUserViewModel allUserViewModel;
    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.toolbar.inflateMenu(R.menu.menusetting);
        setSupportActionBar(binding.toolbar);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        binding.vpChat.setAdapter(adapter);
        binding.tbChat.setupWithViewPager(binding.vpChat);
        fragmentManager = getSupportFragmentManager();
        viewModel = new ViewModelProvider(this).get(AccSettingViewModel.class);
        allUserViewModel = new ViewModelProvider(this).get(AllUserViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusetting, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap img = (Bitmap) extras.get("data");
            viewModel.setAvatar(img);
        } else if (requestCode == 122 && resultCode == RESULT_OK) {
            try {
                Uri uri = data.getData();
                InputStream stream = getContentResolver().openInputStream(uri);
                Bitmap img = BitmapFactory.decodeStream(stream);
                viewModel.setAvatar(img);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.acc_setting:
                AccSettingFragment fragment = new AccSettingFragment();
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.slide_in_down, R.anim.slide_out_down, R.anim.slide_out_up)
                        .replace(R.id.container, fragment).addToBackStack(null).commit();
                break;
            case R.id.all_user:
                AllUserFragment allUserFragment = new AllUserFragment();
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up, R.anim.slide_in_down, R.anim.slide_out_down, R.anim.slide_out_up)
                        .replace(R.id.container, allUserFragment).addToBackStack(null).commit();
                break;
            case R.id.log_out:
                AuthUI.getInstance().signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MainActivity.this,
                                        "You have been signed out.",
                                        Toast.LENGTH_LONG)
                                        .show();

                                // Close activity
                                finish();
                            }
                        });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void status(String status) {
        mRef.child("CSDL").child("User").child(mUser.getUid());
        HashMap<String, Object> map = new HashMap<>();
        map.put("isonline", status);
        mRef.child("CSDL").child("User").child(mUser.getUid()).updateChildren(map);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }
}
