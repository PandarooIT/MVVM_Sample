package com.example.mvvm_sample.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_sample.R;
import com.example.mvvm_sample.databinding.ItemFeatureBinding;
import com.example.mvvm_sample.model.FeatureItem;

public class FeatureAdapter extends ListAdapter<FeatureItem, FeatureAdapter.ViewHolder> {

    OnItemActionListener listener;

    public FeatureAdapter(OnItemActionListener onItemActionListener) {
        super(DIFF_CALLBACK);
        listener = onItemActionListener;
    }

    public interface OnItemActionListener {
        void onItemClick(FeatureItem item, int position);
        void onItemDelete(FeatureItem item, int position);
    }
    private static final DiffUtil.ItemCallback<FeatureItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<FeatureItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull FeatureItem oldItem, @NonNull FeatureItem newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull FeatureItem oldItem, @NonNull FeatureItem newItem) {
                    // So sánh toàn bộ nội dung để biết có cần cập nhật view hay không.
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeatureBinding b = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_feature,
                parent,
                false
        );
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeatureItem item = getItem(position);
        holder.bind(item);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(item, position);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onItemDelete(item, position);
            return true;
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemFeatureBinding binding;

        public ViewHolder(@NonNull ItemFeatureBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FeatureItem item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
