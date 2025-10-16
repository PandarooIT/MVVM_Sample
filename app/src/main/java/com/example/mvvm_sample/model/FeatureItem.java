package com.example.mvvm_sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureItem {
    private int iconRes;
    private String title;

    public int getIconRes() {
        return iconRes;
    }

    public String getTitle() {
        return title;
    }
}
