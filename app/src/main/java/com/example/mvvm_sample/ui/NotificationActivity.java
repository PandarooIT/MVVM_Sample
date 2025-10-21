package com.example.mvvm_sample.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvvm_sample.MVVMApplication;
import com.example.mvvm_sample.R;
import com.example.mvvm_sample.databinding.ActivityMainBinding;
import com.example.mvvm_sample.databinding.ActivityNotificationBinding;
import com.example.mvvm_sample.di.component.ActivityComponent;
import com.example.mvvm_sample.di.component.AppComponent;
import com.example.mvvm_sample.di.component.DaggerActivityComponent;
import com.example.mvvm_sample.model.FeatureItem;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

public class NotificationActivity extends AppCompatActivity {
    @Inject
    ViewModelProvider.Factory factory;
    private FeatureAdapter adapter;
    NotificationViewModel vm;

    private final int[] iconList = {
            R.drawable.ic_car,
            R.drawable.ic_insurance,
            R.drawable.ic_quick_car_valuation,
            R.drawable.ic_traffic_camera
    };



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent appComponent =
                ((MVVMApplication) getApplication()).getAppComponent();
        ActivityComponent activityComponent = DaggerActivityComponent.factory()
                        .create(appComponent, this);
        activityComponent.inject(this);
        ActivityNotificationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
//        FeatureViewModel vm = new ViewModelProvider(this).get(FeatureViewModel.class);
        vm = new ViewModelProvider(this, factory).get(NotificationViewModel.class);
        binding.setVm(vm);
        binding.setLifecycleOwner(this);

        adapter = new FeatureAdapter(new FeatureAdapter.OnItemActionListener() {
            @Override
            public void onItemClick(FeatureItem item, int position) {
                showUpdateDialog(item);
            }

            @Override
            public void onItemDelete(FeatureItem item, int position) {
                new AlertDialog.Builder(NotificationActivity.this)
                        .setTitle("Delete")
                        .setMessage("Delete \"" + item.getTitle() + "\"?")
                        .setPositiveButton("Delete", (d, w) -> vm.deleteFeature(item.getId()))
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
        binding.rvFeatures.setLayoutManager(new GridLayoutManager(this, 5));
        binding.rvFeatures.setAdapter(adapter);
        vm.features.observe(this, list -> adapter.submitList(new ArrayList<>(list)));

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
    }

    private int randomIcon() {
        int index = new java.util.Random().nextInt(iconList.length);
        return iconList[index];
    }

    private void showUpdateDialog(FeatureItem item) {
        EditText input = new EditText(this);
        input.setText(item.getTitle());
        new AlertDialog.Builder(this)
                .setTitle("Update Feature")
                .setView(input)
                .setPositiveButton("Save", (d, w) -> {
                    String newTitle = input.getText().toString();
                    FeatureItem updated = new FeatureItem(item.getId(), item.getIconRes(), newTitle);
                    vm.updateFeature(item.getId(), updated);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showAddDialog() {
        EditText input = new EditText(this);
        input.setHint("Enter feature title");

        new AlertDialog.Builder(this)
                .setTitle("Add Feature")
                .setView(input)
                .setPositiveButton("Add", (dialog, which) -> {
                    String title = input.getText().toString().trim();
                    if (!title.isEmpty()) {
                        int icon = randomIcon();
                        FeatureItem newItem = new FeatureItem(0, icon, title);
                        vm.addFeature(newItem);
                    } else {
                        Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}