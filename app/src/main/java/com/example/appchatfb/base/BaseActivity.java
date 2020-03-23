package com.example.appchatfb.base;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VM extends ViewModel, binding extends ViewBinding> extends ActivityCompat {
    VM viewModel;
    binding binding;

}
