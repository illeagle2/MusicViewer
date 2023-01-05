package com.example.musicviewer.model.remote

import com.example.musicviewer.model.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

    @GET(ENDPOINT)
    suspend fun getRockMusic(
        @Query(PARAM_TERM) musicGenre: String,
        @Query(PARAM_MEDIA) musicMedia: String,
        @Query(PARAM_ENTITY) musicSong: String,
        @Query(PARAM_LIMIT) musicLimit: Double
    ): Call<MusicResponse>
}