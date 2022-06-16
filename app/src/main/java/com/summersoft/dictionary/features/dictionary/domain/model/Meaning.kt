package com.summersoft.dictionary.features.dictionary.domain.model


data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)