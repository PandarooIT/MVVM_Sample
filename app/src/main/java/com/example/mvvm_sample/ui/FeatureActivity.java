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
import com.example.mvvm_sample.ui.base.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

public class FeatureActivity extends BaseActivity<ActivityMainBinding, FeatureViewModel> {
    private FeatureAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<FeatureViewModel> viewModelClass() {
        return FeatureViewModel.class;
    }

    @Override
    protected void injectComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.setA(this);
        binding.setVm(viewModel);

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

        viewModel.features.observe(this, list -> adapter.submitList(new ArrayList<>(list)));
    }

        public void goToNotificationTab() {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        }
}