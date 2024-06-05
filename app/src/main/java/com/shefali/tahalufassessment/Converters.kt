package com.shefali.tahalufassessment

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.let {
            val listType = object : TypeToken<List<String>>() {}.type
            Gson().fromJson<List<String>>(value, listType)
        }
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return list?.let {
            Gson().toJson(it)
        }
    }
}
