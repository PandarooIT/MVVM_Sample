package com.example.mvvm_sample.mapper

import com.example.mvvm_sample.data.realm.FeatureItemEntity
import com.example.mvvm_sample.model.FeatureItem
import java.util.UUID

object FeatureMappers {
    @JvmStatic
    fun toEntity(dto: FeatureItem) : FeatureItemEntity =
        FeatureItemEntity().apply {
            id = (dto.title?:UUID.randomUUID()) as String
            iconRes = dto.iconRes
            title = dto.title
        }
}