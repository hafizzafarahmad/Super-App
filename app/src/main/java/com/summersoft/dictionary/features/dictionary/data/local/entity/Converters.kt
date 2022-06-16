package com.summersoft.dictionary.features.dictionary.data.local.entity

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.summersoft.dictionary.features.dictionary.data.utils.JsonParser
import com.summersoft.dictionary.features.dictionary.domain.model.Meaning

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningJson(json: String): List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json = json,
            type = object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningJson(meanings: List<Meaning>) : String{
        return jsonParser.toJson(
            obj = meanings,
            type = object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }
}