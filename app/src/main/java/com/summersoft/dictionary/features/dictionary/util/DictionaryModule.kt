package com.summersoft.dictionary.features.dictionary.util

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.summersoft.dictionary.features.dictionary.data.local.WordInfoDatabase
import com.summersoft.dictionary.features.dictionary.data.local.entity.Converters
import com.summersoft.dictionary.features.dictionary.data.remote.DictionaryApi
import com.summersoft.dictionary.features.dictionary.data.repository.WordInfoRepositoryImpl
import com.summersoft.dictionary.features.dictionary.data.utils.GsonParser
import com.summersoft.dictionary.features.dictionary.domain.repository.WordInfoRepository
import com.summersoft.dictionary.features.dictionary.domain.use_case.GetWordInfoUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DictionaryModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUsecase(repository: WordInfoRepository): GetWordInfoUsecase {
        return GetWordInfoUsecase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        api: DictionaryApi,
        db: WordInfoDatabase
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api = api, dao = db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase{
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi() : DictionaryApi{
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

}