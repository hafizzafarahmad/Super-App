package com.summersoft.dictionary.features.dictionary.domain.model

import com.google.gson.annotations.SerializedName

data class WordInfo(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val word: String
)
