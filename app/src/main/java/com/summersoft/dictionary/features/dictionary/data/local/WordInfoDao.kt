package com.summersoft.dictionary.features.dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.summersoft.dictionary.features.dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(wordInfos: List<WordInfoEntity>)

    @Query("DELETE FROM WordInfoEntity WHERE word IN(:words)")
    suspend fun deleteWordInfos(words: List<String>)

    @Query("SELECT * FROM WordInfoEntity WHERE word LIKE '%' || :words || '%'")
    suspend fun getInfoWords(words: String) : List<WordInfoEntity>

}