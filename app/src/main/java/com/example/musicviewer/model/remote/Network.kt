package com.example.musicviewer.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    val api: MusicService by lazy {
        initRetrofit().create(MusicService::class.java)
    }

    private fun initRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}