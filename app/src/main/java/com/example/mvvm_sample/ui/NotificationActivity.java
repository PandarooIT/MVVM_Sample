package com.example.mvvm_sample.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvvm_sample.MVVMApplication;
import com.example.mvvm_sample.R;
import com.example.mvvm_sample.databinding.ActivityMainBinding;

import javax.inject.Inject;
import javax.inject.Named;

public class NotificationActivity extends AppCompatActivity {
    @Inject
    @Named("localFactory")
    ViewModelProvider.Factory factory;
    private FeatureAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MVVMApplication)getApplication()).getAppComponent().inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        FeatureViewModel vm = new ViewModelProvider(this).get(FeatureViewModel.class);
        FeatureViewModel vm = new ViewModelProvider(this, factory).get(FeatureViewModel.class);
        binding.setVm(vm);
        binding.setLifecycleOwner(this);

        adapter = new FeatureAdapter();
        binding.rvFeatures.setLayoutManager(new GridLayoutManager(this, 5));
        binding.rvFeatures.setAdapter(adapter);
        vm.features.observe(this, data ->
                adapter.setItems(data)
        );
    }
}