package com.summersoft.dictionary.features.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.summersoft.dictionary.features.dictionary.domain.model.Meaning
import com.summersoft.dictionary.features.dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val origin: String?,
    val phonetic: String,
    val word: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            origin = "",
            phonetic = phonetic,
            word = word
        )
    }
}
