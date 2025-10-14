package com.example.mvvm_sample.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvvm_sample.R;
import com.example.mvvm_sample.databinding.ActivityMainBinding;

public class FeatureActivity extends AppCompatActivity {

    private FeatureAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        FeatureViewModel vm = new ViewModelProvider(this).get(FeatureViewModel.class);

        binding.setVm(vm);
        binding.setLifecycleOwner(this);

        adapter = new FeatureAdapter();
        binding.rvFeatures.setLayoutManager(new GridLayoutManager(this, 4));
        binding.rvFeatures.setAdapter(adapter);
        vm.features.observe(this, data ->
                adapter.setItems(data)
        );
    }
}