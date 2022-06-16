package com.summersoft.dictionary.features.dictionary.domain.use_case

import com.summersoft.dictionary.core.utils.Resource
import com.summersoft.dictionary.features.dictionary.domain.model.WordInfo
import com.summersoft.dictionary.features.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUsecase(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String) : Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()){
            return flow {  }
        }
        return repository.getWordInfoUseCase(word)
    }
}