package com.firdous.saltpayblank.data.local.room

import androidx.room.TypeConverter
import com.firdous.saltpayblank.data.local.entity.ImImageEntity
import com.firdous.saltpayblank.data.remote.model.ImImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromImageList(imageList: List<ImImageEntity?>?): String? {
        val type = object : TypeToken<List<ImImageEntity>>() {}.type
        return Gson().toJson(imageList, type)
    }

    @TypeConverter
    fun toImageList(imageString: String?): List<ImImageEntity>? {
        val type = object : TypeToken<List<ImImageEntity>>() {}.type
        return Gson().fromJson<List<ImImageEntity>>(imageString, type)
    }

}