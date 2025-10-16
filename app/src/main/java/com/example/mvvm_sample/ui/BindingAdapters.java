package com.example.mvvm_sample.ui;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {
    @BindingAdapter("app:srcCompat")
    public static void setImage(ImageView view, int resId) {
        if (resId != 0) {
            view.setImageResource(resId);
        }
    }
}
