package com.example.appchatfb.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public abstract class BaseActivity<VM extends ViewModel, binding extends ViewDataBinding> extends AppCompatActivity {
   @Inject
    ViewModelProvider.Factory viewModelFatory;
   protected VM mviewmodel;
   protected binding mViewBinding;
    public abstract int getBindingVariable();
    public abstract
    @LayoutRes
    int getLayoutId();
    public abstract VM getViewModel();
    public binding getViewDataBinding() {
        return mViewBinding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }
    private void performDataBinding() {
        mViewBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mviewmodel = mviewmodel == null ? getViewModel() : mviewmodel;
        mViewBinding.setVariable(getBindingVariable(), mviewmodel);
        mViewBinding.executePendingBindings();
    }
}
