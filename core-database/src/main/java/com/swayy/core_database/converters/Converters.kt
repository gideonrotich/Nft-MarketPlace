package com.swayy.core_database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swayy.core_network.model.nft_contract.NormalizedMetadata

@ProvidedTypeConverter
class Converters(private val gson: Gson) {
    //to convert the normalizedMetadata
    @TypeConverter
    fun fromTest(str: NormalizedMetadata): String? {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun toTest(str: String?): NormalizedMetadata {
        return Gson().fromJson(str, object : TypeToken<String>() {}.type)
    }
}