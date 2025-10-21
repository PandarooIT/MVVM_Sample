package com.example.mvvm_sample.data;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class FeatureEntity extends RealmObject {
    @PrimaryKey
    private long id;
    private int iconRes;
    @Index
    private String title;
}
