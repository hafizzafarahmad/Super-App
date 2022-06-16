package com.summersoft.dictionary.features.dictionary.presentation

import com.summersoft.dictionary.features.dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
