package com.summersoft.dictionary.features.dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.summersoft.dictionary.features.dictionary.data.local.entity.Converters
import com.summersoft.dictionary.features.dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 2,
)

@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {

    abstract val dao: WordInfoDao

}