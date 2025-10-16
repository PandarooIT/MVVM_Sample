package com.example.mvvm_sample.data.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey

class FeatureItemEntity : RealmObject {
    @PrimaryKey
    var id: String = ""
    var iconRes: Int = 0
    @Index
    var title: String = ""
}