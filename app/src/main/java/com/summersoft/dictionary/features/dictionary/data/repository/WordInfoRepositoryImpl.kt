package com.summersoft.dictionary.features.dictionary.data.repository

import android.util.Log
import com.summersoft.dictionary.core.utils.Resource
import com.summersoft.dictionary.features.dictionary.data.local.WordInfoDao
import com.summersoft.dictionary.features.dictionary.data.remote.DictionaryApi
import com.summersoft.dictionary.features.dictionary.domain.model.WordInfo
import com.summersoft.dictionary.features.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    override fun getWordInfoUseCase(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getInfoWords(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word).map { it.toWordInfoEntity() }
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos)
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Something went wrong!",
                data = wordInfos
            ))
            Log.d("API", e.stackTraceToString())
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getInfoWords(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}