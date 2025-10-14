package com.example.mvvm_sample.ui;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_sample.R;
import com.example.mvvm_sample.databinding.ItemFeatureBinding;
import com.example.mvvm_sample.model.FeatureItem;

import java.util.ArrayList;
import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.VH> {

    private List<FeatureItem> items = new ArrayList<>();

    @NonNull
    @Override
    public FeatureAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeatureBinding b = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_feature, parent, false);
        return new VH(b);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureAdapter.VH holder, int position) {
        if (items != null && !items.isEmpty()) {
            holder.bind(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items != null && !items.isEmpty() ? items.size() : 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<FeatureItem> data) {
        items.clear();
        if (data != null) items.addAll(data);
        notifyDataSetChanged();
    }

    public static class VH extends RecyclerView.ViewHolder {
        ItemFeatureBinding binding;

        public VH(@NonNull ItemFeatureBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind (FeatureItem item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
