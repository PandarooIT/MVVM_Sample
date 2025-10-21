package com.example.mvvm_sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvvm_sample.MVVMApplication;
import com.example.mvvm_sample.R;
import com.example.mvvm_sample.databinding.ActivityMainBinding;
import com.example.mvvm_sample.di.component.ActivityComponent;
import com.example.mvvm_sample.di.component.AppComponent;
import com.example.mvvm_sample.di.component.DaggerActivityComponent;
import com.example.mvvm_sample.model.FeatureItem;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

public class FeatureActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory factory;
    private FeatureAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent appComponent =
                ((MVVMApplication) getApplication()).getAppComponent();
        ActivityComponent activityComponent =
                DaggerActivityComponent.factory()
                        .create(appComponent, this);
        activityComponent.inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        FeatureViewModel vm = new ViewModelProvider(this).get(FeatureViewModel.class);
        FeatureViewModel vm = new ViewModelProvider(this, factory).get(FeatureViewModel.class);
        binding.setA(this);
        binding.setVm(vm);
        binding.setLifecycleOwner(this);

        adapter = new FeatureAdapter(new FeatureAdapter.OnItemActionListener() {
            @Override
            public void onItemClick(FeatureItem item, int position) {

            }

            @Override
            public void onItemDelete(FeatureItem item, int position) {

            }
        });
        binding.rvFeatures.setLayoutManager(new GridLayoutManager(this, 5));
        binding.rvFeatures.setAdapter(adapter);
//        vm.features.observe(this, data ->
//                adapter.submitList(data)
//        );

        vm.features.observe(this, list -> adapter.submitList(new ArrayList<>(list)));
    }

        public void goToNotificationTab() {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        }
}