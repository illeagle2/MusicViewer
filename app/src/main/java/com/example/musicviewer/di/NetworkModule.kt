package com.example.musicviewer.di

import com.example.musicviewer.model.remote.BASE_URL
import com.example.musicviewer.model.remote.MusicService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {


    @Provides
    fun providesRetrofit(
        gsonConverter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverter)
            .build()
    }

    @Provides
    fun providesServiceAPI(retrofit: Retrofit): MusicService {
        return retrofit.create(MusicService::class.java)
    }

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher =
        Dispatchers.IO

    @Provides
    fun providesCoroutineScope(ioDispatcher: CoroutineDispatcher): CoroutineScope =
        CoroutineScope(ioDispatcher)

    @Provides
    fun providesGsonConverter(): Converter.Factory{
        return GsonConverterFactory.create()
    }
}