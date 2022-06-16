package com.summersoft.dictionary.features.dictionary.domain.repository

import com.summersoft.dictionary.core.utils.Resource
import com.summersoft.dictionary.features.dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfoUseCase(word: String) : Flow<Resource<List<WordInfo>>>
}